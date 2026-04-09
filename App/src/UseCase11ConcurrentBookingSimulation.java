import java.util.*;

/**
 * UseCase11ConcurrentBookingSimulation
 *
 * Demonstrates thread-safe booking using synchronized blocks
 *
 * @author Ashmit
 * @version 11.0
 */

// 🔹 Reservation
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return guestName + " -> " + roomType;
    }
}

// 🔹 Shared Queue
class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void add(Reservation r) {
        queue.offer(r);
    }

    public synchronized Reservation get() {
        return queue.poll();
    }
}

// 🔹 Shared Inventory
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
    }

    // 🔥 CRITICAL SECTION
    public synchronized boolean allocateRoom(String type) {

        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("\nFinal Inventory: " + inventory);
    }
}

// 🔹 Booking Processor (Thread)
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        Reservation r;

        while ((r = queue.get()) != null) {

            System.out.println(Thread.currentThread().getName() +
                    " Processing: " + r);

            boolean success = inventory.allocateRoom(r.getRoomType());

            if (success) {
                System.out.println("✅ " + r.getGuestName() + " booked successfully");
            } else {
                System.out.println("❌ " + r.getGuestName() + " booking failed (No rooms)");
            }
        }
    }
}

// 🔹 Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 11.0\n");

        // 🔹 Shared resources
        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        // 🔹 Multiple requests (same room type)
        queue.add(new Reservation("Ashmit", "Single Room"));
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Priya", "Single Room"));

        // 🔹 Multiple threads
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 🔹 Final state
        inventory.display();

        System.out.println("\nConcurrent booking completed safely!");
    }
}