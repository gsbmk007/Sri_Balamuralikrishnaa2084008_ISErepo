import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WhiteBoxTesting {

    public static void main(String[] args) {
        whiteBoxTest_readTemperatureDataFromFile_valid();
        whiteBoxTest_readTemperatureDataFromFile_invalid();
        whiteBoxTest_compareTemperature_warmer();
        whiteBoxTest_compareTemperature_colder();
        whiteBoxTest_compareTemperature_sameTemperature();
        whiteBoxTest_compareTemperature_notFound();
        whiteBoxTest_isFuzzyMatch_exactMatch();
        whiteBoxTest_isFuzzyMatch_fuzzyMatch();
    }

    public static void whiteBoxTest_readTemperatureDataFromFile_valid() {
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");
        // Assert that the temperatures map is not empty
        assert !TemperatureHandler.temperatures.isEmpty() : "Failed to read temperature data from file";
        System.out.println("Testing whiteBoxTest_readTemperatureDataFromFile_valid passed!");
    }

    public static void whiteBoxTest_readTemperatureDataFromFile_invalid() {
        TemperatureHandler.readTemperatureDataFromFile("invalid_file.csv");
        // Assert that the temperatures map is empty
        assert TemperatureHandler.temperatures.isEmpty() : "Expected empty map, but got temperature data";
        System.out.println("Testing whiteBoxTest_readTemperatureDataFromFile_invalid passed!");
    }

    public static void whiteBoxTest_compareTemperature_warmer() {
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");
        String result = TemperatureHandler.compareTemperature("Australia", "Sydney", "morning", 28.5);
        // Assert that the result is "Warmer"
        assert result.equals("Warmer") : "Expected 'Warmer' but got '" + result + "'";
        System.out.println("Testing whiteBoxTest_compareTemperature_warmer passed!");
    }

    public static void whiteBoxTest_compareTemperature_colder() {
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");
        String result = TemperatureHandler.compareTemperature("Australia", "Sydney", "morning", 20.0);
        // Assert that the result is "Colder"
        assert result.equals("Colder") : "Expected 'Colder' but got '" + result + "'";
        System.out.println("Testing whiteBoxTest_compareTemperature_colder passed!");
    }

    public static void whiteBoxTest_compareTemperature_sameTemperature() {
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");
        String result = TemperatureHandler.compareTemperature("Australia", "Sydney", "morning", 25.0);
        // Assert that the result is "Same temperature"
        assert result.equals("Same temperature") : "Expected 'Same temperature' but got '" + result + "'";
        System.out.println("Testing whiteBoxTest_compareTemperature_sameTemperature passed!");
    }

    public static void whiteBoxTest_compareTemperature_notFound() {
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");
        String result = TemperatureHandler.compareTemperature("Invalid Country", "Invalid City", "morning", 25.0);
        // Assert that the result is "Temperature data not found"
        assert result.equals("Temperature data not found") : "Expected 'Temperature data not found' but got '" + result + "'";
        System.out.println("Testing whiteBoxTest_compareTemperature_notFound passed!");
    }

    public static void whiteBoxTest_isFuzzyMatch_exactMatch() {
        boolean result = StringHandler.isFuzzyMatch("Australia", "Australia");
        // Assert that the result is true
        assert result : "Expected true but got false";
        System.out.println("Testing whiteBoxTest_isFuzzyMatch_exactMatch passed!");
    }

    public static void whiteBoxTest_isFuzzyMatch_fuzzyMatch() {
        boolean result = StringHandler.isFuzzyMatch("Austrlia", "Australia");
        // Assert that the result is true
        assert result : "Expected true but got false";
        System.out.println("Testing whiteBoxTest_isFuzzyMatch_fuzzyMatch passed!");
    }
}
