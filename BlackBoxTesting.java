import java.util.Map;

public class BlackBoxTesting {

    public static void main(String[] args) {
        // Test cases for TemperatureHandler
        testCompareTemperature("Japan", "Tokyo", "Morning", 25.3);
        testCompareTemperature("Spain", "Madrid", "Evening", 23.4);
        testCompareTemperature("Sri Lanka", "Colombo", "Morning", 27.6);
        testCompareTemperature("Mauritius", "Curepipe", "Evening", 24.9);
        testCompareTemperature("Malaysia", "Ipoh", "Morning", 24.3);
        testCompareTemperature("Australia", "Perth", "Evening", 28.7);
        testCompareTemperature("Germany", "Berlin", "Morning", 15.0); // Test case not in the CSV file
        testCompareTemperature("Canada", "Toronto", "Evening", -5.0); // Test case not in the CSV file

        // Test cases for SeasonHandlerc
        testGetSeason("Australia", 1, "Summer-Birak");
        testGetSeason("Spain", 4, "Spring");
        testGetSeason("Japan", 9, "Autumn");
        testGetSeason("Mauritius", 12, "Summer");
        testGetSeason("Malaysia", 6, "Southeast Monsoon");
        testGetSeason("Sri Lanka", 3, "Inter-Monsoon");
        testGetSeason("Canada", 13, null);
        testGetSeason("United States", -1, null);
        testGetSeason("Germany", 0, null);
        testGetSeason("India", 6, null);
    }

    public static void testCompareTemperature(String country, String city, String time, double temperature) {
        System.out.println("Testing compareTemperature():");
        System.out.println("Country: " + country);
        System.out.println("City: " + city);
        System.out.println("Time: " + time);
        System.out.println("Temperature: " + temperature);

        String result = TemperatureHandler.compareTemperature(country, city, temperature);
        System.out.println("Result: " + result);
        System.out.println("---------------------------");
    }

    public static void testGetSeason(String country, int month, String expectedSeason) {
        System.out.println("Testing getSeason():");
        System.out.println("Country: " + country);
        System.out.println("Month: " + month);
        System.out.println("Expected Season: " + expectedSeason);

        Map<String, String[]> seasonsData = SeasonHandler.readSeasonsDataFromFile("Seasons.csv");
        String season = SeasonHandler.getSeason(seasonsData, country, month);

        System.out.println("Returned Season: " + season);
        if (expectedSeason != null && expectedSeason.equals(season)) {
            System.out.println("Test Result: PASS");
        } else if (expectedSeason == null && season == null) {
            System.out.println("Test Result: PASS");
        } else {
            System.out.println("Test Result: FAIL");
        }
        System.out.println("---------------------------");
    }
}
