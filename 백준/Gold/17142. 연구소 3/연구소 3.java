import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 활성 바이러스를 고르는 경우의 수를 구함
        // 활성 바이러스를 고르는 최악의 시간 복잡도는 10 C 5 이므로 252가지
        // 252 * bfs 함수의 시간 복잡도
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] lab = new int[n][n];
        List<Coordinate> viruses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    viruses.add(new Coordinate(i, j));
                    lab[i][j] = -2;
                }
                if (value == 1) {
                    lab[i][j] = -1;
                }
            }
        }
        Finder finder = new Finder(viruses, lab, m);
        finder.findCases();
        finder.simulate();
        finder.printResult();
        br.close();
    }


}


class Finder {
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final List<Coordinate> viruses;
    private final List<List<Coordinate>> cases;
    private final int[][] lab;
    private final int numberOfViruses;
    private int time;

    public Finder(List<Coordinate> viruses, int[][] lab, int numberOfViruses) {
        this.viruses = viruses;
        this.cases = new ArrayList<>();
        this.lab = lab;
        this.numberOfViruses = numberOfViruses;
        this.time = Integer.MAX_VALUE;
    }

    public void simulate() {
        for (List<Coordinate> aCase : this.cases) {
            SpreadResult spreadResult = spread(aCase);
            if (isAllContaminated(spreadResult.getVisited())) {
                int time = getMaxTime(spreadResult.getVisited());
                this.time = Math.min(this.time, time);
            }
        }
    }

    public void printResult() {
        if (this.time == Integer.MAX_VALUE) {
            this.time = -1;
        }
        System.out.println(this.time);
    }

    public void findCases() {
        findCase(this.viruses.size(), this.numberOfViruses, 0, 0, new int[this.numberOfViruses]);
    }

    private void findCase(int n, int m, int depth, int start, int[] elements) {
        if (depth == m) {
            List<Coordinate> possibleCase = new ArrayList<>();
            for (int element : elements) {
                possibleCase.add(this.viruses.get(element));
            }
            this.cases.add(possibleCase);
            return;
        }

        for (int i = start; i < n; i++) {
            elements[depth] = i;
            findCase(n, m, depth + 1, i + 1, elements);
        }
    }

    private SpreadResult spread(List<Coordinate> viruses) {
        int[][] simulatedLab = new int[this.lab.length][this.lab.length];
        for (int i = 0; i < this.lab.length; i++) {
            simulatedLab[i] = this.lab[i].clone();
        }
        Queue<Coordinate> queue = new LinkedList<>(viruses);
        int time = 0;
        boolean[][] visited = new boolean[this.lab.length][this.lab.length];
        for (Coordinate coordinate : viruses) {
            visited[coordinate.getRow()][coordinate.getCol()] = true;
            simulatedLab[coordinate.getRow()][coordinate.getCol()] = 0;
        }
        Queue<Coordinate> next = new LinkedList<>();

        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                Coordinate current = queue.poll();
                for (int[] move : MOVES) {
                    int nextRow = current.getRow() + move[0];
                    int nextCol = current.getCol() + move[1];

                    if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && this.lab[nextRow][nextCol] != -1) {
                        visited[nextRow][nextCol] = true;
                        next.offer(new Coordinate(nextRow, nextCol));
                        simulatedLab[nextRow][nextCol] = simulatedLab[current.getRow()][current.getCol()] + 1;
                    }
                }
            }
            if (!next.isEmpty()) {
                queue.addAll(next);
                next.clear();
            }
        }
        return new SpreadResult(simulatedLab, time);
    }

    private boolean needVirusActivating(int row, int col, boolean[][] visited) {
        int count = 0;
        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && this.lab[nextRow][nextCol] != -1) {
                count++;
            }

        }
        return count > 0;
    }

    private boolean isIn(int row, int col) {
        return (0 <= row && row < this.lab.length) && (0 <= col && col < this.lab.length);
    }

    private boolean isAllContaminated(int[][] lab) {
        for (int i = 0; i < this.lab.length; i++) {
            for (int j = 0; j < this.lab.length; j++) {
                if (lab[i][j] == 0 && this.lab[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getMaxTime(int[][] lab) {
        int time = 0;
        for (int i = 0; i < this.lab.length; i++) {
            for (int j = 0; j < this.lab.length; j++) {
                if(this.lab[i][j]==0){
                    time = Math.max(time, lab[i][j]);
                }

            }
        }
        return time;
    }

}

class SpreadResult {
    private final int[][] lab;
    private final int time;

    public SpreadResult(int[][] lab, int time) {
        this.lab = lab;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int[][] getVisited() {
        return lab;
    }
}

class Coordinate {
    private int row;
    private int col;

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

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}