import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Problem5 {
    private abstract static class Plan {
        abstract String type();

        abstract LocalDate renewalDate(LocalDate startDate);
    }

    private static class BasicPlan extends Plan {
        @Override
        String type() {
            return "BASIC";
        }

        @Override
        LocalDate renewalDate(LocalDate startDate) {
            return startDate.plusDays(30);
        }
    }

    private static class StandardPlan extends Plan {
        @Override
        String type() {
            return "STANDARD";
        }

        @Override
        LocalDate renewalDate(LocalDate startDate) {
            return startDate.plusDays(90);
        }
    }

    private static class PremiumPlan extends Plan {
        @Override
        String type() {
            return "PREMIUM";
        }

        @Override
        LocalDate renewalDate(LocalDate startDate) {
            return startDate.plusDays(365);
        }
    }

    private static final Map<String, Plan> PLANS = new HashMap<>();

    static {
        PLANS.put("BASIC", new BasicPlan());
        PLANS.put("STANDARD", new StandardPlan());
        PLANS.put("PREMIUM", new PremiumPlan());
    }

    public static void main(String[] args) {
        // Sample input:
        // 3
        // BASIC Alex 2024-01-01
        // STANDARD Sam 2024-01-01
        // PREMIUM Lee 2024-01-01
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();

        for (int index = 0; index < count; index++) {
            String planType = input.next().toUpperCase(Locale.ROOT);
            String name = input.next();
            LocalDate startDate = LocalDate.parse(input.next());
            Plan plan = PLANS.get(planType);
            if (plan == null) {
                throw new IllegalArgumentException("Unknown plan type: " + planType);
            }

            System.out.printf("%s: %s%n", name, plan.renewalDate(startDate));
        }
    }
}