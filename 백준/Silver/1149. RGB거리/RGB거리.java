import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfHouse = Integer.parseInt(br.readLine());
        // 순서대로 red, green, blue
        int[][] cost = new int[numberOfHouse][3];

        for (int i = 0; i < numberOfHouse; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[numberOfHouse + 1][3];

        for (int i = 1; i <= numberOfHouse; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i - 1][2];
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(dp[numberOfHouse][i], answer);
        }

        System.out.println(answer);

    }
}