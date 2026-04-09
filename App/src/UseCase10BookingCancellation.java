import java.util.*;

/**
 * UseCase10BookingCancellation
 *
 * Demonstrates booking cancellation with rollback using Stack (LIFO)
 *
 * @author Ashmit
 * @version 10.0
 */

// 🔹 Reservation
class Reservation {

    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }

    @Override
    public String toString() {
        return guestName + " | " + roomType + " | " + roomId;
    }
}

// 🔹 Inventory
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increase(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public void display() {
        System.out.println("\nInventory Status:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// 🔹 Booking History
class BookingHistory {

    private List<Reservation> list = new ArrayList<>();

    public void add(Reservation r) {
        list.add(r);
    }

    public boolean remove(Reservation r) {
        return list.remove(r);
    }

    public boolean exists(String roomId) {
        return list.stream().anyMatch(r -> r.getRoomId().equals(roomId));
    }

    public Reservation find(String roomId) {
        for (Reservation r : list) {
            if (r.getRoomId().equals(roomId)) return r;
        }
        return null;
    }

    public void display() {
        System.out.println("\nBooking History:");
        for (Reservation r : list) {
            System.out.println(r);
        }
    }
}

// 🔹 Cancellation Service
class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;

    // 🔹 Stack for rollback
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancelBooking(String roomId) {

        System.out.println("\nCancelling Room ID: " + roomId);

        // 🔹 Validate
        if (!history.exists(roomId)) {
            System.out.println("❌ Invalid Cancellation! Booking not found.");
            return;
        }

        // 🔹 Find reservation
        Reservation r = history.find(roomId);

        // 🔹 Push to stack (rollback tracking)
        rollbackStack.push(roomId);

        // 🔹 Restore inventory
        inventory.increase(r.getRoomType());

        // 🔹 Remove from history
        history.remove(r);

        System.out.println("✅ Booking Cancelled Successfully!");
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
    }
}

// 🔹 Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 10.0\n");

        // 🔹 Initialize
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // 🔹 Add confirmed bookings (from UC6)
        history.add(new Reservation("Ashmit", "Single Room", "SI101"));
        history.add(new Reservation("Rahul", "Double Room", "DO201"));

        // 🔹 Display before cancellation
        history.display();
        inventory.display();

        // 🔹 Cancellation Service
        CancellationService service = new CancellationService(inventory, history);

        // 🔹 Cancel booking
        service.cancelBooking("SI101");

        // 🔹 Invalid cancellation
        service.cancelBooking("XX999");

        // 🔹 Display after cancellation
        history.display();
        inventory.display();

        service.showRollbackStack();

        System.out.println("\nRollback completed successfully!");
    }
}