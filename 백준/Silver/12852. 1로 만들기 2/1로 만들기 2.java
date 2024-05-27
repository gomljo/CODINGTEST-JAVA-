import java.util.Scanner;

public class Main {
    private static final int TARGET = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[][] dp = new int[number + 1][2];
        dp[1] = new int[]{0, 1};
        for (int i = 2; i <= number; i++) {
            if (i % 2 == 0 && i % 3 == 0) {
                int minCost = Math.min(Math.min(dp[i / 2][0], dp[i / 3][0]), dp[i - 1][0]);
                if (minCost == dp[i / 2][0]) {
                    dp[i][0] = dp[i / 2][0] + 1;
                    dp[i][1] = i / 2;
                } else if (minCost == dp[i / 3][0]) {
                    dp[i][0] = dp[i / 3][0] + 1;
                    dp[i][1] = i / 3;
                } else {
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = i - 1;
                }
            } else if (i % 2 == 0) {
                int minCost = Math.min(dp[i / 2][0], dp[i - 1][0]);
                if (minCost == dp[i / 2][0]) {
                    dp[i][0] = dp[i / 2][0] + 1;
                    dp[i][1] = i / 2;
                } else {
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = i - 1;
                }
            } else if (i % 3 == 0) {
                int minCost = Math.min(dp[i / 3][0], dp[i - 1][0]);
                if (minCost == dp[i / 3][0]) {
                    dp[i][0] = dp[i / 3][0] + 1;
                    dp[i][1] = i / 3;
                } else {
                    dp[i][0] = dp[i - 1][0] + 1;
                    dp[i][1] = i - 1;
                }
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = i - 1;
            }
        }
        System.out.println(dp[number][0]);

        while (true) {
            System.out.print(number+" ");
            if(number==1){
                break;
            }
            number = dp[number][1];
        }


        scanner.close();
    }

}