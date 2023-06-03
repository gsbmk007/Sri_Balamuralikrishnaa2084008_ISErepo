import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TemperatureHandler {
    private static HashMap<String, Double> temperatures;

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
        String key = country + "," + city + "," + timeOfDay;

        if (temperatures.containsKey(key)) {
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
}
