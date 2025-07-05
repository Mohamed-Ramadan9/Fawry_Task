package Interfaces;

import java.util.List;

public interface ShippingCalculator {
    double calculateShipping(List<Shippable> items);
    void printShipmentNotice(List<Shippable> items);
}
