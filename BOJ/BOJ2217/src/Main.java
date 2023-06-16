import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfLope = scanner.nextInt();
        int[] lope = new int[numberOfLope];
        for (int i = 0; i < numberOfLope; i++) {
            lope[i] = scanner.nextInt();
        }

        Arrays.sort(lope);
        int maxWeight = 0;

        for (int i = 0; i < numberOfLope; i++) {
            maxWeight = Math.max(maxWeight, lope[i] * (numberOfLope-i));
        }
        System.out.println(maxWeight);

    }
}