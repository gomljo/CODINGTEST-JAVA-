import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // dp인듯?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] health = new int[n];
        int[] joy = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            health[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            joy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[21][101];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 100; j++) {
                if(j-health[i-1]>0){
                    dp[i][j] = Math.max(dp[i - 1][j], joy[i - 1] + dp[i - 1][j-health[i - 1]]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }
        System.out.println(dp[n][100]);
        br.close();
    }
}