package Classes;
import java.util.*;
import Interfaces.Shippable;

import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (product == null || quantity <= 0) throw new IllegalArgumentException();

        Optional<CartItem> existing = items.stream()
                .filter(i -> i.getProduct().getId() == product.getId())
                .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            if (item.getQuantity() + quantity > product.getQuantity()) {
                throw new IllegalStateException("Exceeds available quantity.");
            }
            item.increaseQuantity(quantity);
        } else {
            if (!product.isAvailable(quantity)) {
                throw new IllegalStateException("Not enough in stock.");
            }
            items.add(new CartItem(product, quantity));
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> result = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    result.add((Shippable) item.getProduct());
                }
            }
        }
        return result;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void clear() {
        items.clear();
    }
}
