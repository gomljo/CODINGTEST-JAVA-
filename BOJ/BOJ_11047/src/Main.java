import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int answer = 0;
        Scanner scanner = new Scanner(System.in);
        String parameter = scanner.nextLine();
        int N = Integer.parseInt(parameter.split(" ")[0]);
        int K = Integer.parseInt(parameter.split(" ")[1]);
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[coins.length-1 - i] = scanner.nextInt();
        }

        for (int coin : coins) {
            int cnt = K / coin;
            if (cnt > 0) {
                K -= coin * cnt;
                answer += cnt;
            }
        }
        System.out.println(answer);
    }
}
