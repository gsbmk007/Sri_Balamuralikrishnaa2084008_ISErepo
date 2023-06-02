import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SeasonFinder {
    public static void main(String[] args) {

        // Read the seasons data from the file
        Map<String, String[]> seasonsData =seasonhandler.readSeasonsDataFromFile("Seasons.csv");
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");

        // Prompt the user for input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the country: ");
        String country = scanner.nextLine();
        System.out.print("Enter the month number (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter the city name: ");
        String city = scanner.nextLine();

         city = scanner.nextLine();

        
        System.out.print("Enter the temperature reading: ");
        double temperature = scanner.nextDouble();
        

        // Find and display the corresponding season
        String season = seasonhandler.getSeason(seasonsData, country, month);
        TemperatureHandler.compareTemperature(country, city, temperature);

        if (season != null) {
            System.out.println("The season in " + country + " for month " + month + " is: " + season);
        } else {
            System.out.println("Season data not found for the given country and month.");
        }
    }



    
}
