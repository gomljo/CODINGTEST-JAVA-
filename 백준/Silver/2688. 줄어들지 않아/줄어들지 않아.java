import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestCases; i++) {
            int n = scanner.nextInt();
            long[][] dp = new long[n + 1][10];
            for (int j = 0; j < 10; j++) {
                dp[1][j] = 1;
            }
            for (int j = 2; j <=n; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int l = k; l < 10; l++) {
                        dp[j][k] += dp[j-1][l];
                    }
                }
            }
            long answer = Arrays.stream(dp[n]).sum();
            sb.append(answer+"\n");
        }
        System.out.println(sb);
        scanner.close();
    }

}