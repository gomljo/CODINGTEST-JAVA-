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

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] field = new String[n][m];
        for (int i = 0; i < n; i++) {
            field[i] = br.readLine().split("");
        }
        SurvivorSimulator simulator = new SurvivorSimulator(n, m, field);
        simulator.simulate();
        simulator.printResult();
        br.close();
    }
}

class SurvivorSimulator {
    private static final String LAND = ".";
    private static final String FENCE = "#";
    private static final String WOLF = "v";
    private static final String LAMB = "o";
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int rowSize;
    private int columnSize;
    private String[][] field;
    private int numberOfLamb;
    private int numberOfWolves;
    private boolean[][] visited;

    public SurvivorSimulator(int rowSize, int columnSize, String[][] field) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.field = field;
        this.numberOfLamb = 0;
        this.numberOfWolves = 0;
        this.visited = new boolean[rowSize][columnSize];
    }

    public void simulate() {
        for (int row = 0; row < this.rowSize; row++) {
            for (int column = 0; column < this.columnSize; column++) {
                if (!this.visited[row][column] && isNotFence(row, column)) {
                    investigatePopulation(row, column);
                }
            }
        }
    }


    private void investigatePopulation(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        int numberOfLamb = 0;
        int numberOfWolves = 0;
        if(this.field[row][col].equals(LAMB)){
            numberOfLamb++;
        }
        else if (this.field[row][col].equals(WOLF)){
            numberOfWolves++;
        }
        queue.offer(new int[]{row, col});
        this.visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] location = queue.poll();

            for (int[] move : MOVES) {
                int nextRow = location[0] + move[0];
                int nextColumn = location[1] + move[1];
                if (isIn(nextRow, nextColumn)
                        && !this.visited[nextRow][nextColumn]
                        && isNotFence(nextRow, nextColumn)) {
                    this.visited[nextRow][nextColumn] = true;
                    if (this.field[nextRow][nextColumn].equals(LAMB)) {
                        numberOfLamb++;
                    }

                    if (this.field[nextRow][nextColumn].equals(WOLF)) {
                        numberOfWolves++;
                    }
                    queue.offer(new int[]{nextRow, nextColumn});
                }
            }
        }
        if (numberOfLamb > numberOfWolves) {
            this.numberOfLamb += numberOfLamb;
        } else {
            this.numberOfWolves += numberOfWolves;
        }
    }

    private boolean isNotFence(int row, int column) {
        return !this.field[row][column].equals(FENCE);
    }

    private boolean isIn(int row, int column) {
        return (0 <= row && row < this.rowSize) && (0 <= column && column < this.columnSize);
    }

    public void printResult() {
        System.out.printf("%d %d\n", this.numberOfLamb, this.numberOfWolves);
    }
}