import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[][] triangle = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[size][size+1];
        dp[0][1] = triangle[0][0];
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= i+1; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j-1], dp[i-1][j] + triangle[i][j-1]);
            }
        }
        int answer = 0;
        for (int i = 0; i < dp[size-1].length; i++) {
            answer = Math.max(dp[size-1][i], answer);
        }
        System.out.println(answer);

    }
}