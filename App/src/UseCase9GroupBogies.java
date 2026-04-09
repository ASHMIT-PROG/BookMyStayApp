import java.util.*;
import java.util.stream.*;

/**
 * UseCase9GroupBogies
 *
 * Demonstrates grouping bogies using Collectors.groupingBy()
 *
 * @author Ashmit
 * @version 9.0
 */

// 🔹 Bogie Class
class Bogie {

    private String type;
    private String category; // Passenger / Goods

    public Bogie(String type, String category) {
        this.type = type;
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return type + " (" + category + ")";
    }
}

// 🔹 Main Class
public class UseCase9GroupBogies {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("   Train Consist Management App ");
        System.out.println("=======================================");
        System.out.println("Version: 9.0\n");

        // 🔹 Create Bogie List
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", "Passenger"));
        bogies.add(new Bogie("AC Chair", "Passenger"));
        bogies.add(new Bogie("Sleeper", "Passenger"));
        bogies.add(new Bogie("First Class", "Passenger"));
        bogies.add(new Bogie("Cylindrical", "Goods"));
        bogies.add(new Bogie("Rectangular", "Goods"));

        // 🔹 STREAM + GROUPING
        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getType));

        // 🔹 Display Result
        System.out.println("===== Grouped Bogies =====");

        for (String key : groupedBogies.keySet()) {
            System.out.println("\n" + key + ":");

            for (Bogie b : groupedBogies.get(key)) {
                System.out.println("  " + b);
            }
        }

        System.out.println("\nGrouping completed successfully!");
    }
}