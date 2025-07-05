package Classes;

    public class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }

        public void increaseQuantity(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
            this.quantity += amount;
        }

        public double getTotalPrice() {
            return product.getPrice() * quantity;
        }
    }

