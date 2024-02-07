import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int SKIP = 0;
    private static final int UP = 1;
    private static final int DOWN = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCase = Integer.parseInt(br.readLine());
        int stickerRowSize = 2;
        int numberOfState = 3;
        for (int i = 0; i < numberOfTestCase; i++) {
            int stickerColumnSize = Integer.parseInt(br.readLine());
            int[][] stickers = new int[stickerRowSize+1][stickerColumnSize];
            for (int j = 1; j <= stickerRowSize; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < stickerColumnSize; k++) {
                    stickers[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dp = new int[stickerColumnSize + 1][numberOfState];
            for (int j = 1; j <= stickerColumnSize; j++) {
                dp[j][SKIP] = Math.max(dp[j][SKIP], Math.max(dp[j-1][UP], dp[j-1][DOWN]));
                dp[j][UP] = Math.max(dp[j][UP], Math.max(dp[j-1][DOWN], dp[j-1][SKIP])+stickers[UP][j-1]);
                dp[j][DOWN] = Math.max(dp[j][DOWN], Math.max(dp[j-1][UP], dp[j-1][SKIP])+stickers[DOWN][j-1]);
            }
            int answer = 0;
            for (int j = 0; j < numberOfState; j++) {
                answer = Math.max(dp[stickerColumnSize][j], answer);
            }
            System.out.println(answer);
        }
        br.close();
    }
}