import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] MOVES = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        int[][] table = new int[rowSize][columnSize];
        int numberOfCheese = 0;
        for (int i = 0; i < rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < columnSize; j++) {
                int state = Integer.parseInt(st.nextToken());
                table[i][j] = state;
                if (state == 1) {
                    numberOfCheese++;
                }
            }
        }
        int time = 0;
        while (numberOfCheese != 0) {
            Queue<Block> queue = new LinkedList<>();
            queue.add(new Block(0, 0));
            int[][] contact = new int[rowSize][columnSize];
            contact[0][0] = -1;
            while (!queue.isEmpty()) {
                Block block = queue.poll();

                for (int[] move : MOVES) {
                    int nextRow = block.getRow() + move[0];
                    int nextCol = block.getCol() + move[1];
                    if (!isIn(table, nextRow, nextCol)) {
                        continue;
                    }
                    if (table[nextRow][nextCol] == 1) {
                        contact[nextRow][nextCol]++;
                    }
                    if (table[nextRow][nextCol] == 0 && contact[nextRow][nextCol] == 0) {
                        contact[nextRow][nextCol] = -1;
                        queue.offer(new Block(nextRow, nextCol));
                    }
                }
            }
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    if (contact[i][j] >= 2) {
                        table[i][j] = 0;
                        numberOfCheese--;
                    }
                }
            }
            time++;
        }
        System.out.println(time);
        br.close();

    }

    public static boolean isIn(int[][] table, int nextRow, int nextCol) {
        return (0 <= nextRow && nextRow < table.length) && (0 <= nextCol && nextCol < table[0].length);
    }
}

class Block {
    private final int row;
    private final int col;

    public Block(int row, int col) {
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
        return "Block{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}