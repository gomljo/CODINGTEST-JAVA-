import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static boolean isPrimeNumber(int n) {
        boolean isPrime = true;
        int half = (int) Math.sqrt(n);
        for (int i = 2; i < half + 1; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        int answer = 0;
        List<Integer> primeNumber = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int target = scanner.nextInt();

        for (int i = 2; i <= target; i++) {
            if (isPrimeNumber(i)) {
                primeNumber.add(i);
            }
        }
        int n = primeNumber.size();
        int[] primeNumberArray = primeNumber.stream().mapToInt(Integer::intValue).toArray();
        int[] primeCumSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            primeCumSum[i] = primeCumSum[i - 1] + primeNumberArray[i - 1];
        }
        int start = 0;
        int end = 1;
        while (end < n + 1 && start <= end) {
            if (primeCumSum[end] - primeCumSum[start] < target) {
                end++;
            } else if (primeCumSum[end] - primeCumSum[start] > target) {
                start++;
            } else {
                answer++;
                start++;
            }
        }

        System.out.println(answer);
    }
}