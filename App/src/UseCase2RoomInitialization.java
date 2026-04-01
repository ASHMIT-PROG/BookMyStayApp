/**
 * UseCase2RoomInitialization
 *
 * Demonstrates Room modeling using Abstraction, Inheritance, and Polymorphism.
 * Displays different room types and their static availability.
 *
 * @author Ashmit
 * @version 2.0
 */

// 🔹 Abstract Class
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    // Constructor
    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    // Method to display room details
    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// 🔹 Single Room Class
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 1500);
    }
}

// 🔹 Double Room Class
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 2500);
    }
}

// 🔹 Suite Room Class
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

// 🔹 Main Class (Entry Point)
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Welcome to Book My Stay Application ");
        System.out.println("=======================================");
        System.out.println("Version: 2.0\n");

        // 🔹 Creating Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 🔹 Static Availability Variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // 🔹 Display Details
        System.out.println("---- Room Details ----\n");

        single.displayDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println("----------------------");

        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println("----------------------");

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable);
        System.out.println("----------------------");

        System.out.println("Application executed successfully!");
    }
}