import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        int[][] map = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < columnSize; j++) {
                int state = Integer.parseInt(st.nextToken());
                map[i][j] = state;
            }
        }
        VirusExpansionSimulatorVersion2 virusExpansionSimulatorVersion2 =new VirusExpansionSimulatorVersion2(rowSize, columnSize, map);
        virusExpansionSimulatorVersion2.simulate();
        System.out.println(virusExpansionSimulatorVersion2.getSafeZoneArea());
        br.close();
    }
}

class VirusExpansionSimulatorVersion2 {
    private final int rowSize;
    private final int columnSize;
    private final int[][] map;
    private int safeZoneArea;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public VirusExpansionSimulatorVersion2(int rowSize, int columnSize, int[][] map) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.map = map;
        this.safeZoneArea = 0;
    }

    private int[][] copy(int[][] wantCopied) {
        int[][] newVisited = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            System.arraycopy(wantCopied[i], 0, newVisited[i], 0, columnSize);
        }
        return newVisited;
    }

    public void simulate() {
        makeWall(0);
    }

    private void makeWall(int numberOfWall) {
        if (numberOfWall == 3) {
            int[][] infectionMap = expectExpansion();
            calculateSafeZoneArea(infectionMap);
            return;
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (this.map[i][j] == 0) {
                    this.map[i][j] = 1;
                    makeWall(numberOfWall + 1);
                    this.map[i][j] = 0;
                }
            }
        }
    }
    private void calculateSafeZoneArea(int[][] infectionMap) {
        int safeZoneArea = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (infectionMap[i][j] == 0) {
                    safeZoneArea++;
                }
            }
        }
        this.safeZoneArea = Math.max(this.safeZoneArea, safeZoneArea);
    }

    private int[][] expectExpansion() {
        int[][] copyMap = copy(this.map);
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (this.map[i][j] == 2) {
                    expand(i, j, copyMap);
                }
            }
        }
        return copyMap;
    }


    private void expand(int row, int column, int[][] copyMap) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(row, column));
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (copyMap[current.getRow()][current.getColum()] == 1) {
                continue;
            }
            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextColumn = current.getColum() + move[1];

                if (this.isInMap(nextRow, nextColumn) && copyMap[nextRow][nextColumn] == 0) {
                    copyMap[nextRow][nextColumn] = 2;
                    queue.offer(new Coordinate(nextRow, nextColumn));
                }
            }
        }
    }

    private boolean isInMap(int row, int column) {
        return (0 <= row && row < rowSize) && (0 <= column && column < columnSize);
    }

    public int getSafeZoneArea() {
        return this.safeZoneArea;
    }
}
class Coordinate {
    private final int row;
    private final int colum;

    public Coordinate(int row, int colum) {
        this.row = row;
        this.colum = colum;
    }

    public int getRow() {
        return row;
    }

    public int getColum() {
        return colum;
    }
}