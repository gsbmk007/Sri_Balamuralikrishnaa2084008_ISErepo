import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TemperatureHandler {
    public static HashMap<String, Double> temperatures;

    public static void readTemperatureDataFromFile(String fileName) {
        temperatures = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    String key = values[0] + "," + values[1] + "," + values[2];
                    double temperature = Double.parseDouble(values[3]);
                    temperatures.put(key, temperature);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String compareTemperature(String country, String city, String timeOfDay, double temperature) {
        String key = findMatchingKey(country, city, timeOfDay);

        if (key != null) {
            double storedTemperature = temperatures.get(key);
            if (temperature > storedTemperature) {
                return "Warmer";
            } else if (temperature < storedTemperature) {
                return "Colder";
            } else {
                return "Same temperature";
            }
        } else {
            return "Temperature data not found";
        }
    }

    public static String findMatchingKey(String country, String city, String timeOfDay) {
        for (String key : temperatures.keySet()) {
            String[] parts = key.split(",");
            String storedCountry = parts[0];
            String storedCity = parts[1];
            String storedTimeOfDay = parts[2];

            if (StringHandler.isFuzzyMatch(storedCountry, country)
                    && StringHandler.isFuzzyMatch(storedCity, city)
                    && storedTimeOfDay.equalsIgnoreCase(timeOfDay)) {
                return key;
            }
        }
        return null;
    }
}
