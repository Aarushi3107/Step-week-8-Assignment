import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Problem4 {
    private abstract static class Employee {
        private final String name;
        private final double monthlySalary;

        Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        String name() {
            return name;
        }

        double monthlySalary() {
            return monthlySalary;
        }

        abstract double bonus();
    }

    private static class FulltimeEmployee extends Employee {
        FulltimeEmployee(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        double bonus() {
            return monthlySalary() * 0.10;
        }
    }

    private static class ParttimeEmployee extends Employee {
        ParttimeEmployee(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        double bonus() {
            return monthlySalary() * 0.05;
        }
    }

    private static class Intern extends Employee {
        Intern(String name, double monthlySalary) {
            super(name, monthlySalary);
        }

        @Override
        double bonus() {
            return 2000.0;
        }
    }

    private interface EmployeeFactory {
        Employee create(String name, double monthlySalary);
    }

    private static final Map<String, EmployeeFactory> EMPLOYEE_FACTORIES = new HashMap<>();

    static {
        EMPLOYEE_FACTORIES.put("FULLTIME", FulltimeEmployee::new);
        EMPLOYEE_FACTORIES.put("PARTTIME", ParttimeEmployee::new);
        EMPLOYEE_FACTORIES.put("INTERN", Intern::new);
    }

    public static void main(String[] args) {
        // Sample input:
        // 3
        // FULLTIME Alex 50000
        // PARTTIME Sam 20000
        // INTERN Lee 10000
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        double totalBonus = 0.0;

        for (int index = 0; index < count; index++) {
            String employeeType = input.next().toUpperCase(Locale.ROOT);
            String name = input.next();
            double salary = input.nextDouble();
            EmployeeFactory factory = EMPLOYEE_FACTORIES.get(employeeType);
            if (factory == null) {
                throw new IllegalArgumentException("Unknown employee type: " + employeeType);
            }

            Employee employee = factory.create(name, salary);
            double bonus = employee.bonus();
            System.out.printf(Locale.US, "%s: %.2f%n", employee.name(), bonus);
            totalBonus += bonus;
        }

        System.out.printf(Locale.US, "Total Bonus: %.2f%n", totalBonus);
    }
}