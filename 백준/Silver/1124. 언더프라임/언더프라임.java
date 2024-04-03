import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int MAX = 100_001;
    private static final boolean[] isPrimeNumber = new boolean[MAX];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int answer = 0;
        Arrays.fill(isPrimeNumber, true);
        isPrimeNumber[0] = false;
        isPrimeNumber[1] = false;
        int limit = (int) Math.sqrt(MAX);
        for (int i = 2; i < limit + 1; i++) {
            if (isPrimeNumber[i]) {
                int j = 2;
                while (i * j < MAX) {
                    isPrimeNumber[i * j] = false;
                    j++;
                }
            }
        }

        for (int i = a; i <= b; i++) {
            int res = decomposition(i, 0);
            if (isPrimeNumber[res]) {
                answer++;
            }
        }
        System.out.println(answer);
        scanner.close();
    }

    public static int decomposition(int value, int count) {
        if (value == 1) {
            return count;
        }

        if (isPrimeNumber[value]) {
            return count + 1;
        }

        int dividedNumber = 0;
        for (int i = 2; i <= value; i++) {
            if (isPrimeNumber[i] && value % i == 0) {
                dividedNumber = i;
                break;
            }
        }
        int newCount = 0;
        while (value % dividedNumber == 0) {
            value /= dividedNumber;
            newCount++;
        }
        return decomposition(value, count + newCount);
    }

}