import java.util.*;

/**
 * UseCase6RoomAllocationService
 *
 * Demonstrates booking confirmation and room allocation
 * with uniqueness enforcement and inventory synchronization.
 *
 * @author Ashmit
 * @version 6.0
 */

// 🔹 Reservation (same as UC5)
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Room: " + roomType;
    }
}

// 🔹 Queue (FIFO)
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // REMOVE from queue
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// 🔹 Inventory Service
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// 🔹 Booking Service (MAIN LOGIC)
class BookingService {

    private RoomInventory inventory;

    // 🔹 Track allocated IDs
    private Set<String> allocatedRoomIds = new HashSet<>();

    // 🔹 Map RoomType → Assigned Room IDs
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // 🔹 Generate Unique Room ID
    private String generateRoomId(String roomType) {
        String id;
        do {
            id = roomType.substring(0, 2).toUpperCase() + new Random().nextInt(1000);
        } while (allocatedRoomIds.contains(id)); // ensure uniqueness

        return id;
    }

    // 🔹 Process Booking
    public void processBooking(Reservation r) {

        String type = r.getRoomType();

        System.out.println("\nProcessing -> " + r);

        // 🔹 Check availability
        if (inventory.getAvailability(type) <= 0) {
            System.out.println("❌ No rooms available for " + type);
            return;
        }

        // 🔹 Generate unique ID
        String roomId = generateRoomId(type);

        // 🔹 Store in Set (prevent reuse)
        allocatedRoomIds.add(roomId);

        // 🔹 Map room type → IDs
        roomAllocations.putIfAbsent(type, new HashSet<>());
        roomAllocations.get(type).add(roomId);

        // 🔹 Update inventory (IMPORTANT)
        inventory.reduceAvailability(type);

        // 🔹 Confirmation
        System.out.println("✅ Booking Confirmed!");
        System.out.println("Guest: " + r.getGuestName());
        System.out.println("Room Type: " + type);
        System.out.println("Allocated Room ID: " + roomId);
    }
}

// 🔹 Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 6.0\n");

        // 🔹 Initialize
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        BookingService bookingService = new BookingService(inventory);

        // 🔹 Add Requests (UC5)
        queue.addRequest(new Reservation("Ashmit", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Single Room"));
        queue.addRequest(new Reservation("Priya", "Single Room")); // will fail

        // 🔹 Process Queue (FIFO)
        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            bookingService.processBooking(r);
        }

        System.out.println("\nAll bookings processed.");
    }
}