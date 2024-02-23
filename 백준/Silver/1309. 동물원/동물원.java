import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // 배치하는 방법은 총 3가지이다
    // 1. 아무것도 배치하지 않는다.
    // 2. 왼쪽에 배치
    // 3. 오른쪽에 배치
    // 3개의 상태는 하나의 경우의 수로 간주되며 계속 더해나가면 된다.
    private static final int NO_ASSIGN = 0;
    private static final int LEFT_ASSIGN = 1;
    private static final int RIGHT_ASSIGN = 2;
    private static final int NUMBER_OF_CASE = 3;
    private static final int MOD = 9901;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int columnSize = sc.nextInt();
        int[][] cases = new int[columnSize + 1][NUMBER_OF_CASE];
        for (int i = 0; i < NUMBER_OF_CASE; i++) {
            cases[1][i] = 1;
        }
        for (int i = 2; i <= columnSize; i++) {
            // 아무것도 배치하지 않은 상태
            cases[i][NO_ASSIGN] = ((cases[i - 1][NO_ASSIGN]) + (cases[i - 1][LEFT_ASSIGN]) + (cases[i - 1][RIGHT_ASSIGN])) % MOD;
            // 왼쪽에 배치하는 상태
            cases[i][LEFT_ASSIGN] = ((cases[i - 1][NO_ASSIGN]) + (cases[i - 1][RIGHT_ASSIGN])) % MOD;
            cases[i][RIGHT_ASSIGN] = ((cases[i - 1][NO_ASSIGN]) + (cases[i - 1][LEFT_ASSIGN])) % MOD;
            // 오른쪽에 배치하는 상태
        }
        int answer = (cases[columnSize][NO_ASSIGN] + cases[columnSize][LEFT_ASSIGN] + cases[columnSize][RIGHT_ASSIGN]) % MOD;
        System.out.println(answer);
    }
}