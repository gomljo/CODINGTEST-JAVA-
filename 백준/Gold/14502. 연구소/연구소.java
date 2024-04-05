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
        boolean[][] wallVisited = new boolean[rowSize][columnSize];
        boolean[][] virusVisit = new boolean[rowSize][columnSize];
        List<Coordinate> possibleToWall = new ArrayList<>();
        List<Coordinate> virus = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < columnSize; j++) {
                int state = Integer.parseInt(st.nextToken());
                if (state == 0) {
                    possibleToWall.add(new Coordinate(i, j));
                } else if (state == 1) {
                    wallVisited[i][j] = true;
                } else {
                    virus.add(new Coordinate(i, j));
                    virusVisit[i][j] = true;
                }
                map[i][j] = state;
            }
        }
        VirusExpansionSimulator virusExpansionSimulator = new VirusExpansionSimulator(rowSize, columnSize, map, wallVisited, possibleToWall, virus, virusVisit);
        virusExpansionSimulator.simulate();
        System.out.println(virusExpansionSimulator.getSafeZoneArea());
        br.close();
    }
}

class VirusExpansionSimulator {
    private final int rowSize;
    private final int columnSize;
    private final int[][] map;
    private final List<Coordinate> possibleToWall;
    private final List<Coordinate> virus;
    private final boolean[][] visited;
    private final boolean[][] virusVisited;
    private int safeZoneArea;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public VirusExpansionSimulator(int rowSize, int columnSize, int[][] map, boolean[][] visited,
                                   List<Coordinate> possibleToWall, List<Coordinate> virus,
                                   boolean[][] virusVisited) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.map = map;
        this.visited = visited;
        this.safeZoneArea = 0;
        this.possibleToWall = possibleToWall;
        this.virus = virus;
        this.virusVisited = virusVisited;
    }

    private boolean[][] copy(boolean[][] wantCopied) {
        boolean[][] newVisited = new boolean[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            System.arraycopy(wantCopied[i], 0, newVisited[i], 0, columnSize);
        }
        return newVisited;
    }

    public void simulate() {

        boolean[] wallCheck = new boolean[this.possibleToWall.size()];
        for (int i = 0; i < this.possibleToWall.size(); i++) {
            boolean[][] visited = copy(this.visited);
            makeWall(0, i, visited, wallCheck);
        }
    }

    private int[][] makeNewInfectionMap(boolean[][] afterVirus, boolean[][] visited) {
        int[][] newMap = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (visited[i][j] && map[i][j] == 0) {
                    newMap[i][j] = 3;
                } else if (afterVirus[i][j] && map[i][j] == 1) {
                    newMap[i][j] = 1;
                } else if (afterVirus[i][j] && map[i][j] == 0 || map[i][j] == 2) {
                    newMap[i][j] = 2;
                } else {
                    newMap[i][j] = 0;
                }
            }
        }
        return newMap;
    }


    private void makeWall(int numberOfWall, int index, boolean[][] visited, boolean[] wallCheck) {
        if (numberOfWall == 3) {
            boolean[][] afterVirusExpansion = expectExpansion(visited);
            int[][] infectionMap = makeNewInfectionMap(afterVirusExpansion, visited);
            calculateSafeZoneArea(infectionMap);
            return;
        }

        for (int i = index; i < this.possibleToWall.size(); i++) {
            Coordinate next = this.possibleToWall.get(i);
            if (!wallCheck[i] && !visited[next.getRow()][next.getColum()]) {
                wallCheck[i] = true;
                visited[next.getRow()][next.getColum()] = true;
                makeWall(numberOfWall + 1, i+1, visited, wallCheck);
                visited[next.getRow()][next.getColum()] = false;
                wallCheck[i] = false;
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

    private boolean[][] expectExpansion(boolean[][] visited) {
        boolean[][] virusVisited = copy(visited);
        for (Coordinate eachVirus : this.virus) {
            if (!virusVisited[eachVirus.getRow()][eachVirus.getColum()]) {
                virusVisited[eachVirus.getRow()][eachVirus.getColum()] = true;
                expand(eachVirus, visited, virusVisited);
            }
        }
        return virusVisited;
    }


    private void expand(Coordinate coordinate, boolean[][] visited, boolean[][] virusVisited) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(coordinate);
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (visited[current.getRow()][current.getColum()] && map[current.getRow()][current.getColum()] != 2) {
                continue;
            }
            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextColumn = current.getColum() + move[1];

                if (this.isInMap(nextRow, nextColumn) && !visited[nextRow][nextColumn] && !virusVisited[nextRow][nextColumn]) {
                    virusVisited[nextRow][nextColumn] = true;
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

    private void printInfectionState(boolean[][] virusVisited, boolean[][] visited) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (visited[i][j] && map[i][j] == 0) {
                    System.out.printf("%2d", 3);
                } else if (virusVisited[i][j] && map[i][j] == 1) {
                    System.out.printf("%2d", 1);
                } else if (virusVisited[i][j] && map[i][j] == 0 || map[i][j] == 2) {
                    System.out.printf("%2d", 2);
                } else {
                    System.out.printf("%2d", 0);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printState(boolean[][] afterVirus) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (afterVirus[i][j] && map[i][j] == 0) {
                    System.out.printf("%2d", 3);
                } else if (afterVirus[i][j] && map[i][j] == 2) {
                    System.out.printf("%2d", 2);
                } else if (afterVirus[i][j] && map[i][j] == 1) {
                    System.out.printf("%2d", 1);
                } else {
                    System.out.printf("%2d", 0);
                }
            }
            System.out.println();
        }
        System.out.println();
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