import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ySize = Integer.parseInt(st.nextToken());
        int xSize = Integer.parseInt(st.nextToken());
        int[][] map = new int[ySize][xSize];
        int[] target = new int[2];
        for (int y = 0; y < ySize; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < xSize; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 2) {
                    target[0] = y;
                    target[1] = x;
                }
            }
        }
        DistanceCalculator distanceCalculator = new DistanceCalculator(map, xSize, ySize);
        distanceCalculator.calculate(new Coordinate(target[0], target[1]));
        distanceCalculator.judgeUnreachedLand();
        distanceCalculator.printResult();
        br.close();
    }
}

class DistanceCalculator {
    private static final int[][] MOVES = new int[][]{{0, 1}, {1, 0}, {-1,0}, {0,-1}};
    private final int[][] map;
    private final int[][] distance;
    private final int xSize;
    private final int ySize;

    public DistanceCalculator(int[][] map, int xSize, int ySize) {
        this.map = map;
        this.xSize = xSize;
        this.ySize = ySize;
        this.distance = new int[ySize][xSize];
    }

    public void calculate(Coordinate target) {
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(target.getY(), target.getX(), 0));
        boolean[][] visited = new boolean[this.ySize][this.xSize];
        visited[target.getY()][target.getX()] = true;
        while (!queue.isEmpty()) {
            Path current = queue.poll();
            for (int[] move : MOVES) {
                int nextY = current.getY() + move[0];
                int nextX = current.getX() + move[1];
                if (isIn(nextY, nextX) && isPossibleToGo(nextY, nextX) && !visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    this.distance[nextY][nextX] = current.getDistance() + 1;
                    queue.offer(new Path(nextY, nextX, current.getDistance() + 1));
                }
            }
        }

    }

    private boolean isIn(int y, int x) {
        return (0 <= y && y < this.ySize) && (0 <= x && x < this.xSize);
    }

    private boolean isPossibleToGo(int y, int x) {
        return this.map[y][x] == 1;
    }

    public void printResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.ySize; i++) {
            for (int j = 0; j < this.xSize; j++) {
                stringBuilder.append(this.distance[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
    
    public void judgeUnreachedLand(){
        for (int i = 0; i < this.ySize; i++) {
            for (int j = 0; j < this.xSize; j++) {
                if(this.distance[i][j] == 0 && this.map[i][j]==1){
                    this.distance[i][j] = -1;
                }
            }
        }
    }

}

class Coordinate {
    private final int y;
    private final int x;

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}

class Path extends Coordinate {
    private int distance;

    public Path(int y, int x, int distance) {
        super(y, x);
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return "Path{" +
                "distance=" + distance +
                '}';
    }
}