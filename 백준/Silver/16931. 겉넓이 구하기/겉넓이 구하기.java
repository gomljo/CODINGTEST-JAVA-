import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] squares = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        SurfaceAreaCalculator surfaceAreaCalculator = new SurfaceAreaCalculator(squares, N, M);
        int answer = surfaceAreaCalculator.calculate();
        System.out.println(answer);
    }
}

class SurfaceAreaCalculator {
    private int[][] square;
    private int rowSize;
    private int columnSize;
    private int totalSurfaceArea;

    public SurfaceAreaCalculator(int[][] square, int rowSize, int columnSize) {
        this.square = square;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.totalSurfaceArea = this.rowSize * this.columnSize * 2;
    }

    private void calculateLeftSide() {

        int totalLeftSurfaceArea = 0;
        for (int column = 0; column < this.columnSize; column++) {
            int leftSurfaceArea = 0;
            int height = 0;
            for (int row = 0; row < this.rowSize; row++) {
                if (this.square[row][column] > height) {
                    leftSurfaceArea += this.square[row][column] - height;
                }
                height = this.square[row][column];
            }
            totalLeftSurfaceArea += leftSurfaceArea;
        }
        this.totalSurfaceArea += totalLeftSurfaceArea;
    }

    private void calculateRightSide() {

        int totalRightSurfaceArea = 0;
        for (int column = 0; column < this.columnSize; column++) {
            int rightSurfaceArea = 0;
            int height = 0;
            for (int row = this.rowSize-1; row >= 0; row--) {
                if (this.square[row][column] > height) {
                    rightSurfaceArea += this.square[row][column] - height;
                }
                height = this.square[row][column];
            }
            totalRightSurfaceArea += rightSurfaceArea;
        }
        this.totalSurfaceArea += totalRightSurfaceArea;
    }

    private void calculateFrontSide() {

        for (int row = 0; row < this.rowSize; row++) {
            int frontSurfaceArea = 0;
            int height = 0;
            for (int column = 0; column < this.columnSize; column++) {
                if (this.square[row][column] > height) {
                    frontSurfaceArea += this.square[row][column] - height;
                }
                height = this.square[row][column];
            }
            this.totalSurfaceArea += frontSurfaceArea;
        }
    }

    private void calculateRearSide() {

        for (int row = this.rowSize - 1; row >= 0; row--) {
            int rearSurfaceArea = 0;
            int height = 0;
            for (int column = this.columnSize - 1; column >= 0; column--) {
                if (this.square[row][column] > height) {
                    rearSurfaceArea += this.square[row][column] - height;
                }
                height = this.square[row][column];
            }
            this.totalSurfaceArea += rearSurfaceArea;
        }
    }

    public int calculate() {
        calculateFrontSide();
        calculateRearSide();
        calculateLeftSide();
        calculateRightSide();
        return this.totalSurfaceArea;
    }

}