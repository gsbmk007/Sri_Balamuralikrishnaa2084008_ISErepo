import java.util.Map;

public class SeasonFinderTester {
    public static void main(String[] args) {
        // Create test cases based on the provided data
        String[] countries = {"Australia", "Spain", "Japan", "Mauritius", "Malaysia", "Srilanka"};
        int[] months = {3, 7, -2, 15, 10, 6};
        String[] cities = {"Perth", "Berlin", "Madrid", "Port Louis", "Kuala Lumpur", "Colombo"};
        double[] temperatures = {25.5, 20.2, 30.0, 10.5, 28.0, 32.5};

        // Read the seasons data from the file
        Map<String, String[]> seasonsData = SeasonHandler.readSeasonsDataFromFile("Seasons.csv");
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");

        // Iterate over the test cases and test the program
        for (int i = 0; i < countries.length; i++) {
            String country = countries[i];
            int month = months[i];
            String city = cities[i];
            double temperature = temperatures[i];

            // Find and display the corresponding season
            String season = SeasonHandler.getSeason(seasonsData, country, month);
            String result = TemperatureHandler.compareTemperature(country, city, temperature);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Country: " + country);
            System.out.println("Month: " + month);
            System.out.println("City: " + city);
            System.out.println("Temperature: " + temperature);
            System.out.println("Result: " + result);

            if (season != null) {
                System.out.println("The season in " + country + " for month " + month + " is: " + season);
            } else {
                System.out.println("Season data not found for the given country and month.");
            }

            System.out.println("------------------------------------");
        }
    }
}
