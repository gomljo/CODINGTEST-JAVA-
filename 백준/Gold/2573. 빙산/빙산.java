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
        int ySize = Integer.parseInt(st.nextToken());
        int xSize = Integer.parseInt(st.nextToken());
        int[][] iceBergState = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            iceBergState[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        IceBergMeltingSimulator simulator = new IceBergMeltingSimulator(ySize, xSize, iceBergState, 0);
        simulator.simulate();
        System.out.println(simulator.getHowManyYear());
        br.close();
    }
}

class IceBergMeltingSimulator {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final int[][] iceBergState;
    private int howManyYear;
    private final int ySize;
    private final int xSize;

    public IceBergMeltingSimulator(int ySize, int xSize, int[][] iceBergState, int howManyYear) {
        this.iceBergState = iceBergState;
        this.howManyYear = howManyYear;
        this.ySize = ySize;
        this.xSize = xSize;
    }


    public void simulate() {
        int piece = 0;
        do {
            int[][] amount = measureAmount();
            melt(amount);
            this.howManyYear++;
            piece = isOnePiece();
            if (piece == 0) {
                this.howManyYear = 0;
                break;
            }

        } while (piece < 2);
    }

    public void melt(int[][] amount) {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (amount[i][j] > 0) {
                    this.iceBergState[i][j] = Math.max(0, this.iceBergState[i][j] - amount[i][j]);
                }
            }
        }
    }

    public int[][] measureAmount() {
        int[][] meltingAmount = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (this.iceBergState[i][j] > 0) {
                    int numberOfFacingSea = checkMeltingCondition(new Location(i, j));
                    meltingAmount[i][j] = numberOfFacingSea;
                }
            }
        }
        return meltingAmount;
    }

    public int checkMeltingCondition(Location location) {
        int numberOfFacingSea = 0;
        for (int[] direction : DIRECTIONS) {
            int y = location.getY() + direction[0];
            int x = location.getX() + direction[1];
            if (isIn(y, x) && this.iceBergState[y][x] == 0) {
                numberOfFacingSea++;
            }
        }
        return numberOfFacingSea;
    }

    // bfs를 통해 한 덩어리인지 파악
    public int isOnePiece() {
        boolean[][] visited = new boolean[ySize][xSize];
        int numberOfPiece = 0;
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (this.iceBergState[y][x] > 0 && !visited[y][x]) {
                    visited[y][x] = true;
                    search(new Location(y, x), visited);
                    numberOfPiece++;
                }
            }
        }
        return numberOfPiece;
    }

    public void search(Location location, boolean[][] visited) {
        Queue<Location> queue = new LinkedList<>();
        queue.offer(location);

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            for (int[] direction : DIRECTIONS) {
                int y = current.getY() + direction[0];
                int x = current.getX() + direction[1];
                if (isIn(y, x) && !visited[y][x] && this.iceBergState[y][x] > 0) {
                    visited[y][x] = true;
                    queue.offer(new Location(y, x));
                }
            }
        }
    }

    public boolean isIn(int y, int x) {
        return (0 <= y && y < ySize) && (0 <= x && x < xSize);
    }

    public int[][] getIceBergState() {
        return iceBergState;
    }

    public void printState() {
        System.out.println("melting progress");
        for (int i = 0; i < ySize; i++) {
            System.out.println(Arrays.toString(this.iceBergState[i]));
        }
        System.out.println();
    }

    public int getHowManyYear() {
        return howManyYear;
    }
}

class Location {
    private int y;
    private int x;

    public Location(int y, int x) {
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
        return "Location{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}