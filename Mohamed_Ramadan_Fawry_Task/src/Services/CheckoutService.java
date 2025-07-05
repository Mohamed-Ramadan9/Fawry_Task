package Services;

import Classes.*;
import Interfaces.ReceiptPrinter;
import Interfaces.Shippable;
import Interfaces.ShippingCalculator;
import java.util.List;

public class CheckoutService {
    private final ShippingCalculator shippingCalculator;
    private final ReceiptPrinter receiptPrinter;

    public CheckoutService(ShippingCalculator shippingCalculator, ReceiptPrinter receiptPrinter) {
        this.shippingCalculator = shippingCalculator;
        this.receiptPrinter = receiptPrinter;
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty.");

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product instanceof ExpirableProduct && ((ExpirableProduct) product).isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired.");
            }
            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }
        }

        double subtotal = cart.getSubtotal();
        List<Shippable> shippables = cart.getShippableItems();
        double shipping = shippables.isEmpty() ? 0 : shippingCalculator.calculateShipping(shippables);
        double total = subtotal + shipping;

        if (!customer.canAfford(total)) throw new IllegalStateException("Insufficient balance.");

        customer.deduct(total);
        for (CartItem item : cart.getItems()) {
            item.getProduct().decreaseStock(item.getQuantity());
        }

        if (!shippables.isEmpty()) shippingCalculator.printShipmentNotice(shippables);
        receiptPrinter.printReceipt(cart.getItems(), subtotal, shipping, total, customer.getBalance());
    }
}

