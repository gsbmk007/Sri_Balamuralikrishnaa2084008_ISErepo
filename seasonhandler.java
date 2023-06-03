import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SeasonHandler {
    public static Map<String, String[]> readSeasonsDataFromFile(String filename) {
        Map<String, String[]> seasonsData = new HashMap<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Skip the header line
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String country = parts[0];
                String[] seasons = Arrays.copyOfRange(parts, 1, parts.length);
                seasonsData.put(country, seasons);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }

        return seasonsData;
    }

    public static String getSeason(Map<String, String[]> seasonsData, String country, int month) {
        String[] seasons = null;

        // Look for an exact match first
        seasons = seasonsData.get(country);

        // If no exact match found, perform case-insensitive search
        if (seasons == null) {
            for (Map.Entry<String, String[]> entry : seasonsData.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(country)) {
                    seasons = entry.getValue();
                    break;
                }
            }
        }

        // If no match found yet, perform a fuzzy search by ignoring spelling mistakes
        if (seasons == null) {
            for (Map.Entry<String, String[]> entry : seasonsData.entrySet()) {
                if (StringHandler.isFuzzyMatch(entry.getKey(), country)) {
                    seasons = entry.getValue();
                    break;
                }
            }
        }

        if (seasons != null && month >= 1 && month <= seasons.length) {
            return seasons[month - 1];
        }

        return null;
    }
}
    