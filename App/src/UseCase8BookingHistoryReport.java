import java.util.*;

/**
 * UseCase8BookingHistoryReport
 *
 * Demonstrates booking history storage and reporting.
 * Maintains chronological order using List.
 *
 * @author Ashmit
 * @version 8.0
 */

// 🔹 Reservation (Confirmed Booking)
class Reservation {

    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId;
    }
}

// 🔹 Booking History (Stores all confirmed bookings)
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add booking
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Get all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// 🔹 Report Service
class BookingReportService {

    // Display all bookings
    public void showAllBookings(List<Reservation> list) {

        System.out.println("\n===== Booking History =====");

        if (list.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : list) {
            System.out.println(r);
        }
    }

    // Summary report
    public void generateSummary(List<Reservation> list) {

        System.out.println("\n===== Booking Summary =====");

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : list) {
            summary.put(
                    r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        for (String type : summary.keySet()) {
            System.out.println(type + " Bookings: " + summary.get(type));
        }
    }
}

// 🔹 Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 8.0\n");

        // 🔹 Initialize
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // 🔹 Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("Ashmit", "Single Room", "SI101"));
        history.addReservation(new Reservation("Rahul", "Double Room", "DO201"));
        history.addReservation(new Reservation("Priya", "Suite Room", "SU301"));
        history.addReservation(new Reservation("Amit", "Single Room", "SI102"));

        // 🔹 Show history
        reportService.showAllBookings(history.getAllReservations());

        // 🔹 Generate summary
        reportService.generateSummary(history.getAllReservations());

        System.out.println("\nReporting completed successfully!");
    }
}