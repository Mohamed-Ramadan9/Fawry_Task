package Services;
import Interfaces.Shippable;
import Interfaces.ShippingCalculator;

import java.util.*;
import java.util.stream.Collectors;

public class ShippingService implements ShippingCalculator {
    public double calculateShipping(List<Shippable> items) {
        return items.stream().mapToDouble(Shippable::getWeight).sum() * 1.2;
    }

    public void printShipmentNotice(List<Shippable> items) {
        System.out.println("** Shipment notice **");

        Map<String, Long> grouped = items.stream()
                .collect(Collectors.groupingBy(Shippable::getName, Collectors.counting()));

        for (Map.Entry<String, Long> entry : grouped.entrySet()) {
            double weight = items.stream().filter(i -> i.getName().equals(entry.getKey())).findFirst().get().getWeight();
            System.out.printf("%dx %s %.0fg%n", entry.getValue(), entry.getKey(), weight * 1000);
        }

        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
