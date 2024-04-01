import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int[][] map;
    private static long[][] dp;
    private static int result = 0;
    private static final int[][] MOVES = new int[][]{{0, 1}, {1, 0}};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }
        dp = new long[n + 1][n + 1];
//        bfs(0, 0);
//        System.out.println(result);
//        dfs(0, 0);
        long res = calcNumberOfPathToEnd();
        System.out.println(res);
        br.close();
    }

    public static void bfs(int row, int col) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == n - 1 && current[1] == n - 1) {
                result++;
                continue;
            }

            for (int[] move : MOVES) {
                int distance = map[current[0]][current[1]];
                int nextRow = current[0] + move[0] * distance;
                int nextCol = current[1] + move[1] * distance;
                if (isIn(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }

    public static boolean isIn(int row, int col) {
        return (0 <= row && row < n) && (0 <= col && col < n);
    }

    public static long dfs(int row, int col) {
        if (row == n - 1 && col == n - 1) {
            return 1;
        }

        if (dp[row][col] == 1) {
            return dp[row][col];
        }

        for (int[] move : MOVES) {
            int distance = map[row][col];
            int nextRow = row + move[0] * distance;
            int nextCol = col + move[1] * distance;
            if (isIn(nextRow, nextCol) && dp[nextRow][nextCol] == 0) {
                dp[row][col] = dfs(nextRow, nextCol);
            }
        }
        return dp[row][col];
    }

    public static long calcNumberOfPathToEnd() {
        dp[1][1] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int distance = map[i][j];

                if (dp[i + 1][j + 1] >= 1 && distance != 0) {
                    if (i + distance < n) {
                        dp[i + distance + 1][j + 1] += dp[i + 1][j + 1];
                    }

                    if (j + distance < n) {
                        dp[i + 1][j + distance + 1] += dp[i + 1][j + 1];
                    }
                }
            }
        }
        return dp[n][n];

    }

}