import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Problem2 {
    private abstract static class Vehicle {
        abstract String type();

        abstract double charge(int hours);
    }

    private static class Bike extends Vehicle {
        @Override
        String type() {
            return "BIKE";
        }

        @Override
        double charge(int hours) {
            return hours * 10.0;
        }
    }

    private static class Car extends Vehicle {
        @Override
        String type() {
            return "CAR";
        }

        @Override
        double charge(int hours) {
            if (hours <= 0) {
                return 0.0;
            }
            return 30.0 + (hours - 1) * 20.0;
        }
    }

    private static class Truck extends Vehicle {
        @Override
        String type() {
            return "TRUCK";
        }

        @Override
        double charge(int hours) {
            return Math.max(hours * 50.0, 100.0);
        }
    }

    private static final Map<String, Vehicle> VEHICLES = new HashMap<>();

    static {
        VEHICLES.put("BIKE", new Bike());
        VEHICLES.put("CAR", new Car());
        VEHICLES.put("TRUCK", new Truck());
    }

    public static void main(String[] args) {
        // Sample input:
        // 3
        // BIKE 2
        // CAR 3
        // TRUCK 1
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        double grandTotal = 0.0;

        for (int index = 0; index < count; index++) {
            String vehicleType = input.next().toUpperCase(Locale.ROOT);
            int hours = input.nextInt();
            Vehicle vehicle = VEHICLES.get(vehicleType);
            if (vehicle == null) {
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
            }

            double amount = vehicle.charge(hours);
            System.out.printf(Locale.US, "%s: %.2f%n", vehicle.type(), amount);
            grandTotal += amount;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }
}