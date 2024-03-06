import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        boolean hasZero = false;
        for (int i = 0; i < n; i++) {
            int number = sc.nextInt();
            if (number == 0) {
                hasZero = true;
            } else if (number > 0) {
                positive.add(number);
            } else {
                negative.add(number);
            }

        }
        positive.sort(Comparator.reverseOrder());
        long sum = 0;
        positive.sort(Comparator.reverseOrder());

        for (int i = 0; i < positive.size() - 1; i += 2) {
            if (positive.get(i) != 1 && positive.get(i + 1) != 1) {
                sum += (long) positive.get(i) * positive.get(i + 1);
            } else {
                sum += (positive.get(i) + positive.get(i + 1));
            }
        }
        if (positive.size() % 2 == 1) {
            sum += positive.get(positive.size() - 1);
        }
        negative.sort(Comparator.naturalOrder());
        if (negative.size() % 2 == 1) {
            if (hasZero) {
                negative.remove(negative.size() - 1);
            } else {
                sum += negative.get(negative.size() - 1);
            }

        }
        for (int i = 0; i < negative.size() - 1; i += 2) {
            sum += (long) negative.get(i) * negative.get(i + 1);
        }

        System.out.println(sum);
    }
}