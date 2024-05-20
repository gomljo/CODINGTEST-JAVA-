import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DELTA_ROW = new int[]{0, 0, 1, -1};
    private static final int[] DELTA_COLUMN = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] testbed = new int[size][size];
        PriorityQueue<Block> queue = new PriorityQueue<>(Comparator.comparing(Block::getVirusNumber));

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value != 0) {
                    queue.offer(new Block(i, j, value));
                }
                testbed[i][j] = value;
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken())-1;
        int column = Integer.parseInt(st.nextToken())-1;
        PriorityQueue<Block> next = new PriorityQueue<>(Comparator.comparing(Block::getVirusNumber));
        while (s > 0) {
            while (!queue.isEmpty()) {
                Block current = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = current.getRow() + DELTA_ROW[i];
                    int nextColumn = current.getColumn() + DELTA_COLUMN[i];
                    if ((0 <= nextRow && nextRow < size) && (0 <= nextColumn && nextColumn < size)
                            && testbed[nextRow][nextColumn] == 0) {
                        testbed[nextRow][nextColumn] = current.getVirusNumber();
                        next.offer(new Block(nextRow, nextColumn, current.getVirusNumber()));
                    }
                }
            }
            if(!next.isEmpty()){
                queue.addAll(next);
                next.clear();
            }
            s--;
        }
        System.out.println(testbed[row][column]);
        br.close();
    }
}

class Block {
    private final int row;
    private final int column;
    private final int virusNumber;

    public Block(int row, int column, int virusNumber) {
        this.row = row;
        this.column = column;
        this.virusNumber = virusNumber;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getVirusNumber() {
        return virusNumber;
    }

    @Override
    public String toString() {
        return "Block{" +
                "row=" + row +
                ", column=" + column +
                ", virusNumber=" + virusNumber +
                '}';
    }
}