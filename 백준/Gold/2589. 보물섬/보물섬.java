import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int rowSize;
    private static int colSize;
    private static int[][] map;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < colSize; j++) {
                String value = row[j];
                if (value.equals("L")) {
                    map[i][j] = 1;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] == 1) {
                    boolean[][] visited = new boolean[rowSize][colSize];
                    int maxDistance = search(i, j, visited);
                    answer = Math.max(maxDistance, answer);
                }
            }
        }
        System.out.println(answer);
        br.close();
    }

    public static int search(int row, int col, boolean[][] visited) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(row, col,0));
        visited[row][col] = true;
        int maxDist = 0;
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if(maxDist < current.getDist()){
                maxDist = current.getDist();
            }
            for (int i = 0; i < 4; i++) {
                int nRow = current.getRow() + dx[i];
                int nCol = current.getCol() + dy[i];

                if(isIn(nRow, nCol) && map[nRow][nCol]==1 && !visited[nRow][nCol] ){
                    visited[nRow][nCol] = true;
                    queue.offer(new Coordinate(nRow, nCol, current.getDist()+1));
                }
            }
        }
        return maxDist;
    }

    private static boolean isIn(int row, int col) {
        return (0 <= row && row < rowSize) && (0 <= col && col < colSize);
    }
}

class Coordinate {
    private final int row;
    private final int col;
    private final int dist;
    public Coordinate(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDist() {
        return dist;
    }
}