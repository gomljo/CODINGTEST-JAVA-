import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] MOVES = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = 0;
        int columnSize = 0;
        while (true) {
            int numberOfIsland = 0;
            columnSize = Integer.parseInt(st.nextToken());
            rowSize = Integer.parseInt(st.nextToken());
            if (rowSize == 0 && columnSize == 0) {
                break;
            }
            int[][] map = new int[rowSize][columnSize];

            for (int i = 0; i < rowSize; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < columnSize; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < columnSize; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = 0;
                        Queue<Location> queue = new LinkedList<>();
                        queue.add(new Location(i, j));

                        while (!queue.isEmpty()) {
                            Location current = queue.poll();
                            for (int[] move : MOVES) {
                                int nextRow = current.getRow() + move[0];
                                int nextColumn = current.getColumn() + move[1];
                                if (((0 <= nextRow && nextRow < rowSize)
                                        && (0 <= nextColumn && nextColumn < columnSize))
                                        && map[nextRow][nextColumn] == 1) {
                                    map[nextRow][nextColumn] = 0;
                                    queue.add(new Location(nextRow, nextColumn));
                                }
                            }
                        }
                        numberOfIsland++;
                    }
                }
            }
            System.out.println(numberOfIsland);

            st = new StringTokenizer(br.readLine());
        }

        br.close();
    }
}

class Location {
    private int row;
    private int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}