import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TemperatureHandler {
    private static final Map<String, Map<String, Double>> temperatureData = new HashMap<>();

    public static void readTemperatureDataFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Skip the header line
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String country = parts[0];
                String city = parts[1];
                String time = parts[2];
                double temperature = Double.parseDouble(parts[3]);

                String key = city + "_" + time;
                Map<String, Double> cityTemperatures = temperatureData.getOrDefault(country, new HashMap<>());
                cityTemperatures.put(key, temperature);
                temperatureData.put(country, cityTemperatures);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static String compareTemperature(String country, String city, double temperature) {
        String formattedCountry = formatCountry(country);
        String formattedCity = formatCity(city);
        String result = "";

        String morningKey = formattedCity + "_Morning";
        String eveningKey = formattedCity + "_Evening";

        Map<String, Double> cityTemperatures = temperatureData.get(formattedCountry);
        if (cityTemperatures != null) {
            Double morningAverage = calculateAverage(cityTemperatures, morningKey);
            Double eveningAverage = calculateAverage(cityTemperatures, eveningKey);

            result += "Morning Average Temperature: " + morningAverage + "\n";
            result += "Evening Average Temperature: " + eveningAverage + "\n";

            double minTemperature = Math.min(morningAverage, eveningAverage);
            double maxTemperature = Math.max(morningAverage, eveningAverage);
            double meanTemperature = (morningAverage + eveningAverage) / 2.0;

            result += "Minimum Temperature: " + minTemperature + "\n";
            result += "Maximum Temperature: " + maxTemperature + "\n";
            result += "Mean Temperature: " + meanTemperature + "\n";

            if (temperature < morningAverage && temperature < eveningAverage) {
                result += "The temperature is colder than both morning and evening averages.";
            } else if (temperature < morningAverage || temperature < eveningAverage) {
                result += "The temperature is colder than one of the morning or evening averages.";
            } else {
                result += "The temperature is warmer than both morning and evening averages.";
            }

        } else {
            return "Temperature data not found for the given country and city.";
        }
        return result;

    }

    private static double calculateAverage(Map<String, Double> cityTemperatures, String key) {
        double sum = 0;
        int count = 0;

        for (Map.Entry<String, Double> entry : cityTemperatures.entrySet()) {
            if (entry.getKey().startsWith(key)) {
                sum += entry.getValue();
                count++;
            }
        }

        return count > 0 ? sum / count : 0.0;
    }

    private static String formatCountry(String country) {
        return country.trim();
    }

    private static String formatCity(String city) {
        String[] words = city.trim().split("\\s+");
        StringBuilder formattedCity = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedCity.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return formattedCity.toString().trim();
    }
}
