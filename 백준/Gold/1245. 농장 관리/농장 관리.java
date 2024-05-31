import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1,-1},{1,1},{-1,1},{-1,-1}};
    private static boolean[][] isPeak;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        int answer = 0;
        isPeak = new boolean[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (isPeak[i][j]) {
                    continue;
                }
                boolean[][] visited = new boolean[rowSize][columnSize];
                if (isPossibleToPeak(i, j, visited)) {
                    answer++;
                }
            }
        }
        System.out.println(answer);

        br.close();
    }

    public static boolean isPossibleToPeak(int row, int col, boolean[][] visited) {

        visited[row][col] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        List<int[]> peakList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int[] move : MOVES) {
                int nextRow = pos[0] + move[0];
                int nextCol = pos[1] + move[1];

                if ((0 > nextRow || nextRow >= map.length) || (nextCol < 0 || nextCol >= map[0].length)) {
                    continue;
                }
                if (visited[nextRow][nextCol]) {
                    continue;
                }
                if (map[nextRow][nextCol] > map[pos[0]][pos[1]]) {
                    return false;
                }

                if (map[nextRow][nextCol] == map[pos[0]][pos[1]]) {
                    int[] peak = new int[]{nextRow, nextCol};
                    visited[nextRow][nextCol] = true;
                    queue.offer(peak);
                    peakList.add(peak);
                }
            }

            for (int[] peak : peakList) {
                isPeak[peak[0]][peak[1]] = true;
            }
        }
        return true;
    }
}