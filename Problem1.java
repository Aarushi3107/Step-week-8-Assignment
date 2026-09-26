import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Problem1 {
    private abstract static class Customer {
        abstract String type();

        abstract double finalAmount(double amount);
    }

    private static class Student extends Customer {
        @Override
        String type() {
            return "STUDENT";
        }

        @Override
        double finalAmount(double amount) {
            return amount * 0.90;
        }
    }

    private static class Staff extends Customer {
        @Override
        String type() {
            return "STAFF";
        }

        @Override
        double finalAmount(double amount) {
            return amount * 0.95;
        }
    }

    private static class Guest extends Customer {
        @Override
        String type() {
            return "GUEST";
        }

        @Override
        double finalAmount(double amount) {
            return amount + 10.0;
        }
    }

    private static final Map<String, Customer> CUSTOMERS = new HashMap<>();

    static {
        CUSTOMERS.put("STUDENT", new Student());
        CUSTOMERS.put("STAFF", new Staff());
        CUSTOMERS.put("GUEST", new Guest());
    }

    public static void main(String[] args) {
        // Sample input:
        // 3
        // STUDENT 100
        // STAFF 100
        // GUEST 100
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        double grandTotal = 0.0;

        for (int index = 0; index < count; index++) {
            String customerType = input.next().toUpperCase(Locale.ROOT);
            double amount = input.nextDouble();
            Customer customer = CUSTOMERS.get(customerType);
            if (customer == null) {
                throw new IllegalArgumentException("Unknown customer type: " + customerType);
            }

            double bill = customer.finalAmount(amount);
            System.out.printf(Locale.US, "%s: %.2f%n", customer.type(), bill);
            grandTotal += bill;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }
}