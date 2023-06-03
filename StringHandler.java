public class StringHandler {
    public static boolean isFuzzyMatch(String str1, String str2) {
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
