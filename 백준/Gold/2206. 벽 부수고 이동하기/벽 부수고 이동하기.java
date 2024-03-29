import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int USE_BOMB = 1;
    private static final int NOT_USE_BOMB = 0;
    private static final int WALL = 1;
    private static final int PATH = 0;
    private static int rowSize;
    private static int colSize;
    private static int[][] map;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::valueOf).toArray();
        }
        long res = bfs(0, 0);
        System.out.println(res);
        bufferedReader.close();
    }

    public static int bfs(int row, int col) {
        Queue<Block> queue = new LinkedList<>();
        queue.offer(new Block(row, col, 1, false));
        boolean[][][] visited = new boolean[rowSize][colSize][2];
        visited[0][0][NOT_USE_BOMB] = true;
        while (!queue.isEmpty()) {
            Block current = queue.poll();
            if (current.getRow() == rowSize - 1 && current.getCol() == colSize - 1) {
                return current.getDistance();
            }
            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextCol = current.getCol() + move[1];
                if (!isIn(nextRow, nextCol)) {
                    continue;
                }

                if (map[nextRow][nextCol] == WALL && !current.isBroken() && !visited[nextRow][nextCol][WALL]){
                    visited[nextRow][nextCol][map[nextRow][nextCol]] = true;
                    queue.offer(new Block(nextRow, nextCol, current.getDistance() + 1, true));
                }

                if (map[nextRow][nextCol] == PATH) {
                    if (current.isBroken() && !visited[nextRow][nextCol][USE_BOMB]) {
                        visited[nextRow][nextCol][USE_BOMB] = true;
                        queue.offer(new Block(nextRow, nextCol, current.getDistance() + 1, true));
                    } else if (!current.isBroken() && !visited[nextRow][nextCol][NOT_USE_BOMB]) {
                        visited[nextRow][nextCol][NOT_USE_BOMB] = true;
                        queue.offer(new Block(nextRow, nextCol, current.getDistance() + 1, false));
                    }
                }

            }

        }

        return -1;
    }

    public static boolean isIn(int row, int col) {
        return (0 <= row && row < rowSize) && (0 <= col && col < colSize);
    }
}

class Block {
    private int row;
    private int col;


    private int distance;
    private boolean isBroken;

    public Block(int row, int col, int distance, boolean isBroken) {
        this.row = row;
        this.distance = distance;
        this.col = col;
        this.isBroken = isBroken;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Block{" +
                "row=" + row +
                ", col=" + col +
                ", isBroken=" + isBroken +
                '}';
    }
}