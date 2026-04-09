import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery
 *
 * Demonstrates saving and restoring system state using serialization
 *
 * @author Ashmit
 * @version 12.0
 */

// 🔹 Reservation (Serializable)
class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return guestName + " | " + roomType + " | " + roomId;
    }
}

// 🔹 Inventory (Serializable)
class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// 🔹 System State (Snapshot)
class SystemState implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Reservation> bookings;
    RoomInventory inventory;

    public SystemState(List<Reservation> bookings, RoomInventory inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

// 🔹 Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // 🔹 Save state
    public void save(SystemState state) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("\n✅ System state saved to file.");

        } catch (IOException e) {
            System.out.println("❌ Error saving state: " + e.getMessage());
        }
    }

    // 🔹 Load state
    public SystemState load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("✅ System state loaded from file.");
            return (SystemState) in.readObject();

        } catch (Exception e) {
            System.out.println("⚠️ No previous data found. Starting fresh.");
            return null;
        }
    }
}

// 🔹 Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 12.0\n");

        PersistenceService service = new PersistenceService();

        // 🔹 Try loading existing data
        SystemState state = service.load();

        List<Reservation> bookings;
        RoomInventory inventory;

        if (state != null) {
            bookings = state.bookings;
            inventory = state.inventory;

            System.out.println("\nRecovered Booking History:");
            for (Reservation r : bookings) {
                System.out.println(r);
            }

        } else {
            // 🔹 Fresh start
            bookings = new ArrayList<>();
            inventory = new RoomInventory();

            // Add sample data
            bookings.add(new Reservation("Ashmit", "Single Room", "SI101"));
            bookings.add(new Reservation("Rahul", "Double Room", "DO201"));

            System.out.println("New system initialized.");
        }

        // 🔹 Display inventory
        inventory.display();

        // 🔹 Save state before exit
        SystemState newState = new SystemState(bookings, inventory);
        service.save(newState);

        System.out.println("\nSystem ready with persistence!");
    }
}