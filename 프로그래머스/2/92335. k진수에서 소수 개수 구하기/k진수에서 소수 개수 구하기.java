import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String nDigit = transform(n, k);
        List<Long> candidates = splitByZero(nDigit);

        for (Long candidate : candidates) {
            if (isPrimeNumber(candidate)) {
                answer += 1;
            }
        }
        return answer;
    }

    public static List<Long> splitByZero(String nDigit) {
        return Arrays.stream(nDigit.split("0{1,}")).map(Long::valueOf).collect(Collectors.toList());
    }

    public static boolean isPrimeNumber(long number) {
        if (number < 2) {
            return false;
        }
        int half = (int) Math.sqrt(number);
        for (int i = 3; i <= half; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String transform(int n, int k) {
        Deque<String> digit = new LinkedList<>();
        while (n != 0) {

            if (n % k == 0) {
                digit.offerFirst("0");
            } else {
                digit.offerFirst(String.valueOf(n % k));
            }
            n /= k;
        }
        return String.join("", digit);
    }
}