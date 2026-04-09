import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management using HashMap.
 * Replaces scattered variables with a single source of truth.
 *
 * @author Ashmit
 * @version 3.0
 */

// 🔹 Inventory Class (Centralized Management)
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor - Initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // 🔹 Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // 🔹 Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // 🔹 Display all inventory
    public void displayInventory() {
        System.out.println("---- Room Inventory ----");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// 🔹 Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 3.0\n");

        // 🔹 Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // 🔹 Display Initial Inventory
        inventory.displayInventory();

        // 🔹 Example: Update availability
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // 🔹 Display Updated Inventory
        inventory.displayInventory();

        System.out.println("\nApplication executed successfully!");
    }
}