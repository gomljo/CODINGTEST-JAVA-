import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        for (String word : words) {
            // 회문 판별
            int start = 0;
            int end = word.length() - 1;
            if (isPalindrome(start, end, word)) {
                System.out.println("0");
            } else if (isPseudoPalindrome(start, end, word)) {
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
    }

    public static boolean isPalindrome(int start, int end, String s) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isPseudoPalindrome(int start, int end, String s) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                boolean isLeftMatch = isPalindrome(start + 1, end, s);
                boolean isRightMatch = isPalindrome(start, end - 1, s);
                return isRightMatch || isLeftMatch;
            }
            start++;
            end--;
        }
        return true;
    }
}