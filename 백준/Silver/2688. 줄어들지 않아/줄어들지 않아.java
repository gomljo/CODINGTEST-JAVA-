import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestCases; i++) {
            int n = scanner.nextInt();
            long[][] dp = new long[n + 1][11];
            for (int j = 0; j <= 10; j++) {
                dp[0][j] = 1;
            }
            for (int j = 1; j <=n; j++) {
                for (int k = 1; k <= 10; k++) {
                    dp[j][k] = dp[j][k-1] + dp[j-1][k];
                }
            }
            sb.append(dp[n][10]).append("\n");
        }
        System.out.println(sb);
        scanner.close();
    }
}