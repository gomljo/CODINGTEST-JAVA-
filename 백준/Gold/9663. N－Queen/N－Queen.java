import java.util.Scanner;

public class Main {
    private static int size;
    private static int count;
    private static int[] chessBoard;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        // chessBoard의 i번째 위치의 값은 체스 말을 놓는 열의 값이 된다.
        chessBoard = new int[size];
        nQueen(0);
        System.out.println(count);

    }

    public static void nQueen(int row) {

        if (row == size) {
            count++;
            return;
        }

        for (int column = 0; column < size; column++) {
            chessBoard[row] = column;

            if (isPossible(row)) {
                nQueen(row + 1);
            }
        }

    }

    public static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            // 같은 행에 두었던 적이 있는지
            if (chessBoard[row] == chessBoard[i]) {
                return false;
            }
            // 대각선 상에 두었던 적이 있는지
            // Math.abs(row -i) => 수직 길이
            // Math.abs(chessBoard[row] - chessBoard[i] => 수평 길이
            if (Math.abs(row - i) == Math.abs(chessBoard[row] - chessBoard[i])) {
                return false;
            }

        }

        return true;
    }
}