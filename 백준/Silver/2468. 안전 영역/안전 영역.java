import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int size;
    private static int[][] area;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        area = new int[size][size];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(area[i][j], min);
                max = Math.max(area[i][j], max);
            }
        }
        int answer = 0;
        for (int height = 0; height <= max; height++) {
            boolean[][] visited = new boolean[size][size];
            int numberOfSafeAreaWhenRainfallIsHeight = 0;
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    if (!visited[row][column] && area[row][column] > height) {
                        findSafeArea(row, column, visited, height);
                        numberOfSafeAreaWhenRainfallIsHeight++;
                    }
                }
            }
            answer = Math.max(numberOfSafeAreaWhenRainfallIsHeight, answer);
        }
        System.out.println(answer);

        br.close();
    }

    public static void findSafeArea(int row, int column, boolean[][] visited, int height) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, column});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] move : MOVES) {
                int nextRow = current[0] + move[0];
                int nextColumn = current[1] + move[1];
                if (isIn(nextRow, nextColumn) && !visited[nextRow][nextColumn] && area[nextRow][nextColumn] > height) {
                    visited[nextRow][nextColumn] = true;
                    queue.offer(new int[]{nextRow, nextColumn});
                }
            }
        }
    }

    public static boolean isIn(int row, int column) {
        return (0 <= row && row < size) && (0 <= column && column < size);
    }
}