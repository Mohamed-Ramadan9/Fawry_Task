package Interfaces;

import Classes.CartItem;

import java.util.List;

public interface ReceiptPrinter {
    void printReceipt(List<CartItem> items , double subtotal , double shipping, double total , double customerBalance);
}
