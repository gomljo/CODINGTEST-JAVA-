import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // BFS
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] map = new String[n][m];
        Coordinate doyeonLocation = new Coordinate(0,0);
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = row[j];
                if (map[i][j].equals("I")) {
                    doyeonLocation = new Coordinate(i, j);
                }
            }
        }
        FriendFinder finder = new FriendFinder(map, n, m);
        finder.find(doyeonLocation);
        finder.printResult();
        br.close();
    }
}

class FriendFinder {
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final String NO_FRIENDS = "TT";
    private static final String PATH = "O";
    private static final String WALL = "X";
    private static final String FRIEND = "P";
    private String[][] campus;
    private int rowSize;
    private int columnSize;
    private int numberOfFriends;

    public FriendFinder(String[][] campus, int rowSize, int columnSize) {
        this.campus = campus;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.numberOfFriends = 0;
    }

    public void find(Coordinate doyeonLocation) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(doyeonLocation);
        boolean[][] visited = new boolean[this.rowSize][this.columnSize];
        visited[doyeonLocation.getRow()][doyeonLocation.getCol()] = true;
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextCol = current.getCol() + move[1];

                if (isIn(nextRow, nextCol) && !isWALL(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    if (isFriend(nextRow, nextCol)) {
                        this.numberOfFriends++;
                    }
                    queue.offer(new Coordinate(nextRow, nextCol));
                }
            }
        }
    }

    private boolean isIn(int row, int col) {
        return (0 <= row && row < this.rowSize) && (0 <= col && col < this.columnSize);
    }

    private boolean isWALL(int row, int col) {
        return this.campus[row][col].equals(WALL);
    }

    private boolean isFriend(int row, int col) {
        return this.campus[row][col].equals(FRIEND);
    }

    public void printResult() {
        if (this.numberOfFriends > 0) {
            System.out.println(this.numberOfFriends);
        } else {
            System.out.println(NO_FRIENDS);
        }
    }


}

class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}