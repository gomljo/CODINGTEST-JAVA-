import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int R;
    static int C;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        R = input[0];
        C = input[1];
        map = new int[R][C];
        dp = new int[R][C];

        for (int r = 0; r < R; r++) {
            String[] info = br.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(info[c]);
                dp[r][c] = -1;
            }
        }

        System.out.println(dfs(0,0));

    }

    private static int dfs(int r, int c) {
        if (r == R - 1 && c == C - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (map[r][c] > map[nr][nc]) {
                    dp[r][c] += dfs(nr, nc);
                }
            }
        }

        return dp[r][c];
    }
}