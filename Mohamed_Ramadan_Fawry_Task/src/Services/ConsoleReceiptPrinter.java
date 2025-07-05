package Services;

import Classes.CartItem;
import Interfaces.ReceiptPrinter;

import java.util.List;

public class ConsoleReceiptPrinter implements ReceiptPrinter {
    public void printReceipt(List<CartItem> items, double subtotal, double shipping, double total, double customerBalance) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.printf("%dx %s %.0f%n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.2f%n", subtotal);
        System.out.printf("Shipping %.2f%n", shipping);
        System.out.printf("Amount %.2f%n", total);
        System.out.printf("Remaining Balance %.2f%n", customerBalance);
    }
}