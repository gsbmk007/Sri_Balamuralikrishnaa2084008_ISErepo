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
                if (isFuzzyMatch(entry.getKey(), country)) {
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
    
    private static boolean isFuzzyMatch(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
    
        // Calculate the Levenshtein distance between the two strings
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
    
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }
    
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }
    
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int delete = dp[i - 1][j] + 1;
                    int insert = dp[i][j - 1] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(delete, insert), replace);
                }
            }
        }
    
        // Check if the Levenshtein distance is within an acceptable threshold
        int maxAllowedDistance = Math.max(str1.length(), str2.length()) / 2;
        return dp[str1.length()][str2.length()] <= maxAllowedDistance;
    }
    
}
