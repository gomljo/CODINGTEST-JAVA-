import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[][] chessBoard;
    private static int possible = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] chessBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            chessBoard[0][i] = 1;
            nQueen(0, i, chessBoard);
            chessBoard[0][i] = 0;
        }
        System.out.println(possible);
        scanner.close();
    }

    public static void nQueen(int row, int column, int[][] chessBoard) {
        if (row == chessBoard.length-1) {
            possible++;
            return;
        }
        for (int j = 0; j < chessBoard.length; j++) {
            if (j == column - 1 || j == column + 1 || j == column) {
                continue;
            }
            if (isCheckMate(row + 1, j, chessBoard)) {
                continue;
            }
            chessBoard[row+1][j] = 1;
            nQueen(row + 1, j, chessBoard);
            chessBoard[row+1][j] = 0;
        }


    }

    public static boolean isCheckMate(int row, int column, int[][] chessBoard) {
        if (checkNorth(row, column, chessBoard)) {
            return true;
        }
        if (checkNorthWest(row, column, chessBoard)) {
            return true;
        }
        if (checkNorthEast(row, column, chessBoard)) {
            return true;
        }
        return false;
    }

    public static boolean checkNorth(int row, int column, int[][] chessBoard) {
        while (row > 0) {
            if (chessBoard[row - 1][column] == 1) {
                return true;
            }
            row--;
        }
        return false;
    }

    public static boolean checkNorthEast(int row, int column, int[][] chessBoard) {
        while (row > 0 && column < chessBoard.length - 1) {
            if (chessBoard[row - 1][column + 1] == 1) {
                return true;
            }
            row--;
            column++;
        }
        return false;
    }

    public static boolean checkNorthWest(int row, int column, int[][] chessBoard) {
        while (row > 0 && column > 0) {
            if (chessBoard[row - 1][column - 1] == 1) {
                return true;
            }
            row--;
            column--;
        }
        return false;
    }
}