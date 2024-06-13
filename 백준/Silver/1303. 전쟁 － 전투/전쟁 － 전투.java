import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 영역의 크기를 추출한 뒤에 제곱해서 같은 것끼리 더하면 됨
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int colSize = Integer.parseInt(st.nextToken());
        int rowSize = Integer.parseInt(st.nextToken());
        String[][] battleField = new String[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            battleField[i] = br.readLine().split("");
        }

        CombatPowerInspector whiteSide = new CombatPowerInspector("W", 0, battleField);
        CombatPowerInspector blueSide = new CombatPowerInspector("B", 0, battleField);
        whiteSide.findSameSide();
        int whiteSideCombatPower = whiteSide.getCombatPower();

        blueSide.findSameSide();
        int blueSideCombatPower = blueSide.getCombatPower();
        System.out.println(whiteSideCombatPower + " " + blueSideCombatPower);

        br.close();
    }
}

class CombatPowerInspector {
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final String side;
    private Integer combatPower;
    private final String[][] battleField;

    public CombatPowerInspector(String side, Integer combatPower, String[][] battleField) {
        this.side = side;
        this.combatPower = combatPower;
        this.battleField = battleField;
    }

    public void findSameSide() {
        boolean[][] visited = new boolean[this.battleField.length][this.battleField[0].length];
        for (int row = 0; row < this.battleField.length; row++) {
            for (int col = 0; col < this.battleField[row].length; col++) {
                if (this.battleField[row][col].equals(this.side) && !visited[row][col]) {
                    int numberOfSoldiers = calculateHowManySoldiersTogether(row, col, visited);
                    this.combatPower += (int)Math.pow(numberOfSoldiers, 2);
                }
            }
        }
    }

    private int calculateHowManySoldiersTogether(int row, int col, boolean[][] visited) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int numberOfSoldiers = 1;
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] move : MOVES) {
                int nextRow = current[0] + move[0];
                int nextCol = current[1] + move[1];
                if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol] && isSameSide(nextRow, nextCol)) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                    numberOfSoldiers++;
                }
            }
        }
        return numberOfSoldiers;
    }

    private boolean isIn(int row, int col) {
        return (0 <= row && row < this.battleField.length) && (0 <= col && col < this.battleField[0].length);
    }

    private boolean isSameSide(int row, int col) {
        return this.battleField[row][col].equals(this.side);
    }

    public int getCombatPower(){
        return this.combatPower;
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