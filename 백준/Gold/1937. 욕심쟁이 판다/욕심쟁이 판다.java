import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] forest;
    private static int[][] dp;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        forest = new int[size][size];
        dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        int answer = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int row, int col) {

        if (dp[row][col]!=-1) {
            return dp[row][col];
        }
        dp[row][col] = 1;
        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];
            if ((0 <= nextRow && nextRow < forest.length) && (0 <= nextCol && nextCol < forest[0].length)) {
                if(forest[nextRow][nextCol] > forest[row][col]){
                    dp[row][col] = Math.max(dp[row][col], dfs(nextRow, nextCol)+1);
                }
            }
        }
        return dp[row][col];
    }
}