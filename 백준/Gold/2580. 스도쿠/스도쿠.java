import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] sudoku;
    private static final int MAX_ROW_SIZE = 9;
    private static final int MAX_COLUMN_SIZE = 9;

    // 백트래킹
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        for (int i = 0; i < MAX_ROW_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < MAX_COLUMN_SIZE; j++) {
                int number = Integer.parseInt(st.nextToken());
                sudoku[i][j] = number;
            }
        }
        fillSudoku(0, 0);
    }

    public static boolean isCorrectSquare(int row, int col, int value) {
        int rowPos = row / 3;
        int colPos = col / 3;
        for (int i = 3 * rowPos; i < 3 * (rowPos + 1); i++) {
            for (int j = 3 * colPos; j < 3 * (colPos + 1); j++) {
                if (sudoku[i][j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCorrectVertical(int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == value) {
                return false;
            }

        }
        return true;
    }

    public static boolean isCorrectHorizontal(int row, int value) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == value) {
               return false;
            }
        }
        return true;
    }

    public static void fillSudoku(int row, int col) {
        if (col == MAX_COLUMN_SIZE) {
            fillSudoku(row + 1, 0);
            return;
        }
        if (row == MAX_ROW_SIZE) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < MAX_ROW_SIZE; i++) {
                for (int j = 0; j < MAX_COLUMN_SIZE; j++) {
                    sb.append(sudoku[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }


        if (sudoku[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (isCorrectHorizontal(row, i)
                        && isCorrectVertical(col, i)
                        && isCorrectSquare(row, col, i)) {
                    sudoku[row][col] = i;
                    fillSudoku(row, col + 1);
                }
            }
            sudoku[row][col] = 0;
            return;
        }
        fillSudoku(row, col + 1);
    }
}