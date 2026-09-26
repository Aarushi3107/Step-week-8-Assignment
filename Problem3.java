import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Problem3 {
    private abstract static class Room {
        abstract String type();

        abstract double bill();
    }

    private static class SingleRoom extends Room {
        private final double units;

        SingleRoom(double units) {
            this.units = units;
        }

        @Override
        String type() {
            return "SINGLE";
        }

        @Override
        double bill() {
            return units * 8.0;
        }
    }

    private static class SharedRoom extends Room {
        private final double units;
        private final int occupants;

        SharedRoom(double units, int occupants) {
            this.units = units;
            this.occupants = occupants;
        }

        @Override
        String type() {
            return "SHARED";
        }

        @Override
        double bill() {
            return units * 6.0 / occupants;
        }
    }

    private static class AcRoom extends Room {
        private final double units;

        AcRoom(double units) {
            this.units = units;
        }

        @Override
        String type() {
            return "AC";
        }

        @Override
        double bill() {
            return units * 10.0 + 200.0;
        }
    }

    private interface RoomFactory {
        Room create(String[] fields);
    }

    private static final Map<String, RoomFactory> ROOM_FACTORIES = new HashMap<>();

    static {
        ROOM_FACTORIES.put("SINGLE", fields -> new SingleRoom(Double.parseDouble(fields[1])));
        ROOM_FACTORIES.put("SHARED", fields -> new SharedRoom(
                Double.parseDouble(fields[1]), Integer.parseInt(fields[2])));
        ROOM_FACTORIES.put("AC", fields -> new AcRoom(Double.parseDouble(fields[1])));
    }

    public static void main(String[] args) throws IOException {
        // Sample input:
        // 3
        // SINGLE 100
        // SHARED 120 2
        // AC 50
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(input.readLine().trim());
        double grandTotal = 0.0;

        for (int index = 0; index < count; index++) {
            String[] fields = input.readLine().trim().split("\\s+");
            String roomType = fields[0].toUpperCase(Locale.ROOT);
            RoomFactory factory = ROOM_FACTORIES.get(roomType);
            if (factory == null) {
                throw new IllegalArgumentException("Unknown room type: " + roomType);
            }

            Room room = factory.create(fields);
            double amount = room.bill();
            System.out.printf(Locale.US, "%s: %.2f%n", room.type(), amount);
            grandTotal += amount;
        }

        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }
}