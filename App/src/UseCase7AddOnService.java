import java.util.*;

/**
 * UseCase7AddOnService
 *
 * Demonstrates add-on service selection for booked rooms.
 * Example: Food, Laundry, Spa
 *
 * @author Ashmit
 * @version 7.0
 */

class AddOnService {

    private Map<String, List<String>> services = new HashMap<>();

    // Add service to a room
    public void addService(String roomId, String service) {
        services.putIfAbsent(roomId, new ArrayList<>());
        services.get(roomId).add(service);

        System.out.println("Added Service -> " + service + " for Room ID: " + roomId);
    }

    // View services for a room
    public void viewServices(String roomId) {
        System.out.println("\nServices for Room ID: " + roomId);

        List<String> list = services.get(roomId);

        if (list == null || list.isEmpty()) {
            System.out.println("No services added.");
            return;
        }

        for (String s : list) {
            System.out.println("- " + s);
        }
    }
}

public class UseCase7AddOnService {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 7.0\n");

        AddOnService service = new AddOnService();

        // Simulate room IDs from UC6
        String room1 = "SI101";
        String room2 = "DO202";

        // Add services
        service.addService(room1, "Food");
        service.addService(room1, "Laundry");
        service.addService(room2, "Spa");

        // View services
        service.viewServices(room1);
        service.viewServices(room2);

        System.out.println("\nAdd-on services processed successfully!");
    }
}