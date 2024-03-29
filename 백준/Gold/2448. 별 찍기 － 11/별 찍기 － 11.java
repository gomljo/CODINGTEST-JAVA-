import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static char[][] starMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = n / 3;
        int coef = (int) (Math.log10(k) / Math.log10(2));
        starMap = new char[n][n * 2 - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(starMap[i], ' ');
        }
        printStars(0, (n * 2 - 1) / 2, coef, n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(starMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void printStars(int row, int col, int k, int height) {
        if (k == 0) {
            print(row, col);
            return;
        }
        printStars(row, col, k - 1, height / 2);
        printStars(row + height / 2, col - (height / 2), k - 1, height / 2);
        printStars(row + height / 2, col + (height / 2), k - 1, height / 2);
    }

    public static void print(int row, int col) {
        eachPrint(row, col);


    }

    public static void eachPrint(int row, int col) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= i; j++) {
                starMap[i + row][col - j] = '*';
                starMap[i + row][col + j] = '*';
            }
        }
        starMap[row + 1][col] = ' ';
    }
}