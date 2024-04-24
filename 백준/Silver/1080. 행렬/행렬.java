import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int TRANSFORM_SIZE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        MatrixConverter matrixConverter = new MatrixConverter(n, m, TRANSFORM_SIZE);
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.stream(scanner.next().split("")).mapToInt(Integer::valueOf).toArray();
        }
        for (int i = 0; i < n; i++) {
            b[i] = Arrays.stream(scanner.next().split("")).mapToInt(Integer::valueOf).toArray();
        }
        matrixConverter.convert(a, b);

        if (matrixConverter.isSame(a, b)) {
            matrixConverter.printAnswer();
        }
        else {
            matrixConverter.printNotPossible();
        }
        scanner.close();
    }
}

class MatrixConverter {
    private final int n;
    private final int m;
    private final int transformSize;
    private int answer;

    public MatrixConverter(int n, int m, int transformSize) {
        this.n = n;
        this.m = m;
        this.transformSize = transformSize;
        this.answer = 0;
    }

    public void convert(int[][] a, int[][] b) {
        for (int i = 0; i < this.n - this.transformSize + 1; i++) {
            for (int j = 0; j < this.m - this.transformSize + 1; j++) {
                if (a[i][j] != b[i][j]) {
                    changeValue(a, i, j);
                    this.answer++;
                }
            }
        }
    }

    private void changeValue(int[][] a, int row, int col) {
        for (int i = 0; i < this.transformSize; i++) {
            for (int j = 0; j < this.transformSize; j++) {
                if (a[row + i][col + j] == 1) {
                    a[row + i][col + j] = 0;
                } else {
                    a[row + i][col + j] = 1;
                }
            }
        }
    }

    public boolean isPossibleToConvert() {
        return this.n >= this.transformSize && this.m >= this.transformSize;
    }

    public int getAnswer() {
        return answer;
    }

    public boolean isSame(int[][] a, int[][] b) {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printAnswer() {
        System.out.println(this.answer);
    }

    public void printNotPossible(){
        System.out.println(-1);
    }
}