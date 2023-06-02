import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class seasonhandler {
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
        String[] seasons = seasonsData.get(country);

        if (seasons != null && month >= 1 && month <= seasons.length) {
            return seasons[month - 1];
        }

        return null;
    }
    
}
