import Classes.*;
import Interfaces.ReceiptPrinter;
import Interfaces.ShippingCalculator;
import Services.CheckoutService;
import Services.ConsoleReceiptPrinter;
import Services.ShippingService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ShippingCalculator shippingService = new ShippingService();
        ReceiptPrinter receiptPrinter = new ConsoleReceiptPrinter();
        CheckoutService checkout = new CheckoutService(shippingService, receiptPrinter);

        System.out.println("===  Valid Case: Mixed Products ===");
        try {
            Customer customer = new Customer("Mohamed", 1000);
            Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, LocalDate.now().plusDays(2), 0.2);
            Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(2), 0.7);
            Product scratchCard = new DigitalProduct("Scratch Card", 50, 20);

            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n===  Expired Product ===");
        try {
            Customer customer = new Customer("Ahmed", 500);
            Product expired = new ExpirableShippableProduct("Cheese", 80, 5, LocalDate.now().minusDays(1), 0.3);
            Cart cart = new Cart();
            cart.add(expired, 1);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n===  Empty Cart ===");
        try {
            Customer customer = new Customer("Sara", 500);
            Cart cart = new Cart();
            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n===  Insufficient Balance ===");
        try {
            Customer customer = new Customer("Ehab", 20);
            Product tv = new ShippableProduct("TV", 300, 2, 5.0);
            Cart cart = new Cart();
            cart.add(tv, 1);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n===  Add More Than Available ===");
        try {
            Customer customer = new Customer("Ali", 1000);
            Product cheese = new ExpirableShippableProduct("Cheese", 100, 3, LocalDate.now().plusDays(2), 0.2);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(cheese, 2);  // Should throw an exception
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n=== Digital Product Only (No Shipping) ===");
        try {
            Customer customer = new Customer("Mona", 100);
            Product card = new DigitalProduct("Recharge Card", 40, 10);
            Cart cart = new Cart();
            cart.add(card, 2);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n=== Shippable Only ===");
        try {
            Customer customer = new Customer("Ziad", 1000);
            Product tv = new ShippableProduct("TV", 300, 3, 5.0);
            Product chair = new ShippableProduct("Chair", 100, 4, 3.0);
            Cart cart = new Cart();
            cart.add(tv, 1);
            cart.add(chair, 2);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }

        System.out.println("\n===  Balance and Stock Check ===");
        try {
            Customer customer = new Customer("Essam", 500);
            Product bag = new ExpirableShippableProduct("Bag", 100, 5, LocalDate.now().plusDays(1), 1.0);
            Cart cart = new Cart();
            cart.add(bag, 3);

            checkout.checkout(customer, cart);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex.getMessage());
        }
    }
}
