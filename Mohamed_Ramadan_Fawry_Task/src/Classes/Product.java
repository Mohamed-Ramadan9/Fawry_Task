package Classes;

public abstract class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (!isAvailable(quantity)) throw new IllegalStateException("Not enough stock");
        this.quantity -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        this.quantity += quantity;
    }

    public void updatePrice(double newPrice) {
        if (newPrice < 0) throw new IllegalArgumentException("Price must be >= 0");
        this.price = newPrice;
    }
}
