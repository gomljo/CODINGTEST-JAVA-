import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 2XN 타일링
        // DP 점화식, 규칙
        // 점화식으로 풀어야하는 문제는 항상 규칙이 숨어있다.
        // 찾기가 어려울 뿐
        // 상향식 접근도 가능하고
        // 하향식 접근도 가능할 것 같은데
        // 상향식은 n이 작을 때 처리해주어야할 일이 많아서
        // 귀찮다.
        // 하향식 접근 => 피보나치 수열의 재귀 표현을 생각하면 된다
        // 상향식 접근 => 피보나치 수열의 반복문 표현을 생각하면 된다.
        int answer = 0;
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        scanner.close();
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
