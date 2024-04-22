import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        int[][] maze = new int[rowSize][columnSize];

        for (int i = 0; i < rowSize; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
        }
        MazeRunner mazeRunner = new MazeRunner(maze, rowSize, columnSize);
        mazeRunner.doExit();
        System.out.println(mazeRunner.getMinimumDistance());
        br.close();
    }
}

class MazeRunner {
    private static final int[][] MOVES = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    private int[][] maze;
    private int rowSize;
    private int columnSize;

    public int getMinimumDistance() {
        return minimumDistance;
    }

    private int minimumDistance;


    public MazeRunner(int[][] maze, int rowSize, int columnSize) {
        this.maze = maze;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public void doExit() {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(0, 0, 0));
        boolean[][] visited = new boolean[this.rowSize][this.columnSize];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (isExit(current)) {
                this.minimumDistance = current.getNumberOfMove()+1;
                break;
            }

            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextColumn = current.getColumn() + move[1];
                if (isInMaze(nextRow, nextColumn) && !visited[nextRow][nextColumn] && isPossibleToGo(nextRow,nextColumn)) {
                    visited[nextRow][nextColumn] = true;
                    queue.offer(new Coordinate(nextRow, nextColumn, current.getNumberOfMove() + 1));
                }
            }
        }
    }

    private boolean isPossibleToGo(int nextRow, int nextColumn) {
        return this.maze[nextRow][nextColumn] == 1;
    }

    private boolean isInMaze(int nextRow, int nextColumn) {
        return (0 <= nextRow && nextRow < this.rowSize) && (0 <= nextColumn && nextColumn < this.columnSize);
    }

    private boolean isExit(Coordinate coordinate) {
        return coordinate.getRow() == this.rowSize - 1 && coordinate.getColumn() == this.columnSize - 1;
    }


}

class Coordinate {
    private final int row;
    private final int column;

    public int getNumberOfMove() {
        return numberOfMove;
    }

    private final int numberOfMove;

    public Coordinate(int row, int column, int numberOfMove) {
        this.row = row;
        this.column = column;
        this.numberOfMove = numberOfMove;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}