import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SeasonFinder {
    // public static void main(String[] args) {

    //     // Read the seasons data from the file
    //     Map<String, String[]> seasonsData =seasonhandler.readSeasonsDataFromFile("Seasons.csv");

    //     // Prompt the user for input
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter the country: ");
    //     String country = scanner.nextLine();
    //     System.out.print("Enter the month number (1-12): ");
    //     int month = scanner.nextInt();

    //     // Find and display the corresponding season
    //     String season = seasonhandler.getSeason(seasonsData, country, month);
    //     if (season != null) {
    //         System.out.println("The season in " + country + " for month " + month + " is: " + season);
    //     } else {
    //         System.out.println("Season data not found for the given country and month.");
    //     }
    // }

    public static void main(String[] args) {
        String filename = "temps.csv";
        TemperatureHandler.readTemperatureDataFromFile(filename);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the country name: ");
        String country = scanner.nextLine();

        System.out.print("Enter the city name: ");
        String city = scanner.nextLine();

        System.out.print("Enter the temperature reading: ");
        double temperature = scanner.nextDouble();

        scanner.close();

        TemperatureHandler.compareTemperature(country, city, temperature);
    }


    
}
