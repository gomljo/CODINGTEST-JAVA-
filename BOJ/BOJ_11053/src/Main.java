import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //수열 arr의 길이
        int[] arr = new int[n+1];  //수열 배열 초기화
        int[] dp = new int[n+1];  //dp 테이블 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());  //arr에 수열 입력 받기
            dp[i] = 1;  //모든 dp값들은 최소 1
        }
        int max = 1;
        for (int i = 1; i <= n; i++) {
            System.out.println("=".repeat(10) + "first loop" + "=".repeat(10));
            for (int j = 1; j < i; j++) {
                System.out.printf("%s %s [i=%d, j=%d] %s\n" , "<".repeat(10),  "second loop", i, j, ">".repeat(10));
                System.out.printf("arr[%d]: %d, arr[%d]: %d\n", i, arr[i], j, arr[j]);
                if (arr[i] > arr[j]) {
                    System.out.printf("arr[%d] is less than arr[%d]\n", j, i);
                    System.out.printf("Math.max(dp[%d], dp[%d]+1) = Math.max(%d, %d) = %d\n",
                            i, j, dp[i], dp[j]+1, Math.max(dp[i], dp[j]+1));
                    dp[i] = Math.max(dp[i], dp[j]+1);

                }
                System.out.println("<".repeat(25) + ">".repeat(25));
            }
            System.out.printf("%s %s %s\n" , "#".repeat(10),  "second loop result", "#".repeat(10));
            System.out.println("dp Array: "+ Arrays.toString(dp));
            System.out.printf("before compare max value: %d\n", max);
            max = Math.max(max, dp[i]);  //LIS 길이 구하기
            System.out.printf("after compare max value: %d\n", max);
            System.out.println("#".repeat(30));
        }
        System.out.print(max);
    }
}