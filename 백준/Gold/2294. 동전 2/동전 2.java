import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int k = Integer.parseInt(params[1]);
        int[] coins = new int[n];
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            coins[i] = value;
        }
        for (int j = 0; j < n; j++) {
            for (int i = coins[j]; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
            }
        }
        if(dp[k]==100001){
            System.out.println("-1");
        }
        else {
            System.out.println(dp[k]);
        }

    }
}