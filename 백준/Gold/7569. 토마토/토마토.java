import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][][] visited;
    private static int[][][] tomatoBox;
    private static int height;
    private static int rowSize;
    private static int columnSize;
    private static final int[][] MOVES = new int[][]{{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        columnSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        tomatoBox = new int[height][rowSize][columnSize];
        visited = new boolean[height][rowSize][columnSize];
        int numberOfRipe = 0;
        int numberOfUnRipe = 0;
        int empty = 0;
        Queue<Coordinate> currentQueue = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < rowSize; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < columnSize; k++) {
                    tomatoBox[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomatoBox[i][j][k] == -1) {
                        visited[i][j][k] = true;
                        empty++;
                    } else if (tomatoBox[i][j][k] == 1) {
                        numberOfRipe++;
                        currentQueue.add(new Coordinate(i, j, k));
                    } else {
                        numberOfUnRipe++;
                    }
                }
            }
        }
        if (numberOfRipe + empty == rowSize * columnSize * height) {
            System.out.println("0");
            return;
        }

        int timeDuration = 0;

        Queue<Coordinate> nextQueue = new LinkedList<>();
        while (!currentQueue.isEmpty()) {
            while (!currentQueue.isEmpty()) {
                Coordinate current = currentQueue.poll();
                if (isAlreadyVisited(current)) {
                    continue;
                }
                checkVisited(current);

                for (int[] move : MOVES) {
                    int nextHeight = current.getHeight() + move[0];
                    int nextRow = current.getRow() + move[1];
                    int nextColumn = current.getColumn() + move[2];
                    if (isInBox(nextHeight, nextRow, nextColumn)
                            && isUnRipe(nextHeight, nextRow, nextColumn)
                            && !visited[nextHeight][nextRow][nextColumn]) {
                        numberOfUnRipe--;
                        Coordinate next = new Coordinate(nextHeight, nextRow, nextColumn);
                        checkRipe(nextHeight, nextRow, nextColumn);
                        nextQueue.add(next);
                    }
                }
            }
            currentQueue.addAll(nextQueue);
            nextQueue.clear();
            if(currentQueue.isEmpty()){
                break;
            }
            timeDuration++;
        }
        if (numberOfUnRipe > 0) {
            System.out.println(-1);
        } else {
            System.out.println(timeDuration);
        }


    }

    public static void printBox(int[][][] box) {
        for (int i = 0; i < height; i++) {
            System.out.println((i+1) + "'s box state");
            for (int j = 0; j < rowSize; j++) {
                System.out.print("[ ");
                for (int k = 0; k < columnSize; k++) {
                    System.out.printf("%d ", box[i][j][k]);
                }
                System.out.print("]\n");
            }
        }
    }

    public static boolean isAlreadyVisited(Coordinate coordinate) {
        return visited[coordinate.getHeight()][coordinate.getRow()][coordinate.getColumn()];
    }

    public static void checkVisited(Coordinate coordinate) {
        visited[coordinate.getHeight()][coordinate.getRow()][coordinate.getColumn()] = true;
    }

    public static boolean isUnRipe(int nextHeight, int nextRow, int nextColumn) {
        return tomatoBox[nextHeight][nextRow][nextColumn] == 0;
    }

    public static void checkRipe(int nextHeight, int nextRow, int nextColumn) {
        tomatoBox[nextHeight][nextRow][nextColumn] = 1;
    }

    public static boolean isInBox(int nextHeight, int nextRow, int nextColumn) {
        return isRowInRange(nextRow) && isColumnInRange(nextColumn) && isHeightInRange(nextHeight);
    }

    private static boolean isRowInRange(int nextRow) {
        return 0 <= nextRow && nextRow < rowSize;
    }

    private static boolean isColumnInRange(int nextColumn) {
        return 0 <= nextColumn && nextColumn < columnSize;
    }

    private static boolean isHeightInRange(int nextHeight) {
        return 0 <= nextHeight && nextHeight < height;
    }
}

class Coordinate {
    private final int height;
    private final int row;
    private final int column;

    public Coordinate(int height, int row, int column) {
        this.row = row;
        this.column = column;
        this.height = height;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getHeight() {
        return this.height;
    }
}