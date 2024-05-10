import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private final static int SIZE = 19;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        OmokWinnerDiscriminator omokWinnerDiscriminator = new OmokWinnerDiscriminator(board, SIZE);
        omokWinnerDiscriminator.discriminate();
        omokWinnerDiscriminator.printWinner();
        br.close();
    }
}

class OmokWinnerDiscriminator {
    private static final int MAX_DEPTH = 5;
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private static final int EMPTY = 0;

    private static final int DRAW = 0;
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    private int[][] board;
    private int winner;
    private int[] mostLeftStone;
    private int size;
    private boolean[][][] visited;

    public OmokWinnerDiscriminator(int[][] board, int size) {
        this.board = board;
        this.winner = DRAW;
        this.size = size;
        this.visited = new boolean[this.size][this.size][DIRECTIONS.length];
        this.mostLeftStone = new int[2];
    }

    public void discriminate() {
        boolean isFind = false;
        for (int column = 0; column < this.size; column++) {
            if(isFind){
                break;
            }
            for (int row = 0; row < this.size; row++) {
                if (this.board[row][column] != EMPTY) {
                    for (int i = 0; i < DIRECTIONS.length; i++) {
                        if(isFind){
                            break;
                        }
                        if (this.visited[row][column][i]){
                            continue;
                        }
                        this.visited[row][column][i] = true;
                        int count = calculateContinuousCount(row, column, i);
                        if (count == MAX_DEPTH) {
                            this.winner = this.board[row][column];
                            this.mostLeftStone[0] = row;
                            this.mostLeftStone[1] = column;
                            isFind=true;
                        }
                    }
                }
            }
        }
    }

    private int calculateContinuousCount(int row, int col, int directionIndex) {
        int nextRow = row + DIRECTIONS[directionIndex][0];
        int nextCol = col + DIRECTIONS[directionIndex][1];
        if (isIn(nextRow, nextCol) && this.board[nextRow][nextCol] == this.board[row][col]) {
            this.visited[nextRow][nextCol][directionIndex] = true;
            return calculateContinuousCount(nextRow, nextCol, directionIndex) + 1;
        }
        return 1;
    }

    private boolean isIn(int row, int col) {
        return (0 <= row && row < this.size) && (0 <= col && col < this.size);
    }

    public void printWinner() {
        System.out.println(this.winner);
        if (this.winner != DRAW) {
            for (int pos : this.mostLeftStone) {
                System.out.print((pos + 1) + " ");
            }
            System.out.println();
        }

    }

    private int[] findMostLeftStone(int[][] fiveStones) {
        Arrays.sort(fiveStones, (stone, otherStone) -> stone[0] == otherStone[0] ? stone[1] - otherStone[1] : stone[0] - otherStone[0]);
        return fiveStones[0];
    }

}