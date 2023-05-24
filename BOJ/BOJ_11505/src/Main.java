import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] arr = new int[n+1];
        int[] dp = new int[n+1];
        for (int i = 1; i <=n; i++) {
            int element = scanner.nextInt();
            arr[i] = element;
        }
        dp[1] = arr[1];
        for (int i = 2; i <=n; i++) {
            dp[i] = arr[i];
            for (int j = 1; j <i; j++) {
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[j]+ arr[i], dp[i]);

                }
            }
        }
        Arrays.sort(dp);
        System.out.println(dp[n]);

    }

}
