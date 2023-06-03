import java.util.Map;

public class SeasonsTesting {

    public static void main(String[] args) {
        Map<String, String[]> seasonsData = SeasonHandler.readSeasonsDataFromFile("Seasons.csv");

        // Valid country, valid month
        String result1 = SeasonHandler.getSeason(seasonsData, "Australia", 3);
        String expected1 = "Autumn-Bunuru";
        System.out.println("------------------------------");
        System.out.println("Test Case 1:");
        System.out.println("Input: Australia, Month: 3");
        System.out.println("Expected Output: " + expected1);
        System.out.println("Output: " + result1);
        System.out.println("Test Result: " + (result1.equals(expected1) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Valid country, valid month
        String result2 = SeasonHandler.getSeason(seasonsData, "Srilanka", 7);
        String expected2 = "Southeast Monsoon";
        System.out.println("Test Case 2:");
        System.out.println("Input: Srilanka, Month: 7");
        System.out.println("Expected Output: " + expected2);
        System.out.println("Output: " + result2);
        System.out.println("Test Result: " + (result2.equals(expected2) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Valid country, valid month
        String result3 = SeasonHandler.getSeason(seasonsData, "Japan", 12);
        String expected3 = "Winter";
        System.out.println("Test Case 3:");
        System.out.println("Input: Japan, Month: 12");
        System.out.println("Expected Output: " + expected3);
        System.out.println("Output: " + result3);
        System.out.println("Test Result: " + (result3.equals(expected3) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Invalid country, invalid month
        String result4 = SeasonHandler.getSeason(seasonsData, "InvalidCountry", 13);
        String expected4 = "Unknown";
        System.out.println("Test Case 4:");
        System.out.println("Input: InvalidCountry, Month: 13");
        System.out.println("Expected Output: " + expected4);
        System.out.println("Output: " + result4);
        System.out.println("Test Result: " + (result4.equals(expected4) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Invalid month, valid country
        String result5 = SeasonHandler.getSeason(seasonsData, "Australia", 13);
        String expected5 = "Unknown";
        System.out.println("Test Case 5:");
        System.out.println("Input: Australia, Month: 13");
        System.out.println("Expected Output: " + expected5);
        System.out.println("Output: " + result5);
        System.out.println("Test Result: " + (result5.equals(expected5) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Valid country, invalid month
        String result6 = SeasonHandler.getSeason(seasonsData, "France", 0);
        String expected6 = "Unknown";
        System.out.println("Test Case 6:");
        System.out.println("Input: France, Month: 0");
        System.out.println("Expected Output: " + expected6);
        System.out.println("Output: " + result6);
        System.out.println("Test Result: " + (result6.equals(expected6) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Boundary Value Analysis Testing
        System.out.println("Boundary Value Analysis Testing:");
        System.out.println("------------------------------");

        // Test lower bound - First month (January)
        String result7 = SeasonHandler.getSeason(seasonsData, "Malaysia", 12);
        String expected7 = "NortheastMonsoon";
        System.out.println("Test Case 7:");
        System.out.println("Input: Australia, Month: 1");
        System.out.println("Expected Output: " + expected7);
        System.out.println("Output: " + result7);
        System.out.println("Test Result: " + (result7.equals(expected7) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Test upper bound - Last month (December)
        String result8 = SeasonHandler.getSeason(seasonsData, "Malaysia", 2);
        String expected8 = "NortheastMonsoon";
        System.out.println("Test Case 8:");
        System.out.println("Input: Malaysia, Month: 12");
        System.out.println("Expected Output: " + expected8);
        System.out.println("Output: " + result8);
        System.out.println("Test Result: " + (result8.equals(expected8) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        String result9 = SeasonHandler.getSeason(seasonsData, "Japan", 12);
        String expected9 = "Winter";
        System.out.println("Test Case 9:");
        System.out.println("Input: Japan, Month: 1");
        System.out.println("Expected Output: " + expected9);
        System.out.println("Output: " + result9);
        System.out.println("Test Result: " + (result9.equals(expected9) ? "Passed" : "Failed"));
        System.out.println("------------------------------");

        // Test upper bound - Last month (December)
        String result10 = SeasonHandler.getSeason(seasonsData, "Japan", 2);
        String expected10 = "Winter";
        System.out.println("Test Case 10:");
        System.out.println("Input: Japan, Month: 12");
        System.out.println("Expected Output: " + expected10);
        System.out.println("Output: " + result10);
        System.out.println("Test Result: " + (result10.equals(expected10) ? "Passed" : "Failed"));
        System.out.println("------------------------------");
    }
}
