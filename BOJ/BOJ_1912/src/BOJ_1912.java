import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N];
        dp[0] = numbers[0];
        int maxVal=numbers[0];

        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1]+numbers[i], numbers[i]);
            maxVal = Math.max(maxVal, dp[i]);
        }
        System.out.println(maxVal);

    }

}
