import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int numberOfHouse = sc.nextInt();
        int numberOfRouter = sc.nextInt();
        int[] houses = new int[numberOfHouse];

        for (int i = 0; i < numberOfHouse; i++) {
            houses[i] = sc.nextInt();
        }
        Arrays.sort(houses);
        int coveringStart = 1;
        int coveringEnd = houses[numberOfHouse - 1] - houses[0] + 1;
        while (coveringStart <= coveringEnd) {
            int coveringRange = (coveringStart + coveringEnd) / 2;
            int count = calculateRouterCount(houses, coveringRange);
            if (count < numberOfRouter) {
                coveringEnd = coveringRange-1;
            } else {
                coveringStart = coveringRange + 1;
            }
        }
        System.out.println(coveringEnd);
    }

    public static int calculateRouterCount(int[] houses, int coveringRange) {
        int count = 1;
        int current = houses[0];
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - current >= coveringRange) {
                count++;
                current = houses[i];
            }
        }
        return count;
    }

}