import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            int[] dp = new int[11];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;

            for (int j = 2; j <= k; j++) {
                for (int l = 1; l <= 3; l++) {
                    if(j-l >= 0){
                        dp[j] += dp[j-l];
                    }
                }
            }
            System.out.println(dp[k]);

        }

    }
}