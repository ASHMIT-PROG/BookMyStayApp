import java.util.*;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates READ-ONLY room search using centralized inventory.
 * Ensures no modification to system state.
 *
 * @author Ashmit
 * @version 4.0
 */

// 🔹 Room Domain Model
class Room {
    private String type;
    private int price;
    private String amenities;

    public Room(String type, int price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

// 🔹 Inventory (Same as UC3 but read-only here)
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}

// 🔹 Search Service (READ ONLY)
class SearchService {

    private RoomInventory inventory;
    private Map<String, Room> roomData;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
        this.roomData = new HashMap<>();

        // Room details (Domain Model)
        roomData.put("Single Room", new Room("Single Room", 2000, "WiFi, TV"));
        roomData.put("Double Room", new Room("Double Room", 3500, "WiFi, TV, AC"));
        roomData.put("Suite Room", new Room("Suite Room", 5000, "WiFi, TV, AC, Mini Bar"));
    }

    // 🔹 READ ONLY SEARCH
    public void searchAvailableRooms() {
        System.out.println("---- Available Rooms ----");

        for (String type : inventory.getAllRoomTypes()) {

            int available = inventory.getAvailability(type);

            // 🔹 Filter unavailable rooms
            if (available > 0) {

                Room room = roomData.get(type);

                System.out.println("Room Type : " + room.getType());
                System.out.println("Price     : ₹" + room.getPrice());
                System.out.println("Amenities : " + room.getAmenities());
                System.out.println("Available : " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

// 🔹 Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 4.0\n");

        // 🔹 Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // 🔹 Initialize Search Service
        SearchService searchService = new SearchService(inventory);

        // 🔹 Perform Search (READ ONLY)
        searchService.searchAvailableRooms();

        System.out.println("\nSearch completed successfully!");
    }
}