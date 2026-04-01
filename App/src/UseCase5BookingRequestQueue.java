import java.util.*;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates booking request handling using Queue (FIFO).
 * Ensures fair request ordering without modifying inventory.
 *
 * @author Ashmit
 * @version 5.0
 */

// 🔹 Reservation Class (Booking Request)
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

// 🔹 Booking Request Queue (FIFO)
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // 🔹 Add request (enqueue)
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request Added -> " + reservation);
    }

    // 🔹 View all requests
    public void displayQueue() {
        System.out.println("\n---- Booking Request Queue ----");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            System.out.println(r);
        }
    }

    // 🔹 Get next request (peek only, no removal)
    public Reservation peekNextRequest() {
        return queue.peek();
    }
}

// 🔹 Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 5.0\n");

        // 🔹 Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // 🔹 Simulate Guest Requests
        bookingQueue.addRequest(new Reservation("Ashmit", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));

        // 🔹 Display Queue (FIFO Order)
        bookingQueue.displayQueue();

        // 🔹 Show Next Request (without removing)
        System.out.println("\nNext Request to Process:");
        System.out.println(bookingQueue.peekNextRequest());

        System.out.println("\nAll requests stored successfully (No allocation done yet).");
    }
}