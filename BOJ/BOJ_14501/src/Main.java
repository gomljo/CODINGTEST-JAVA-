import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] talk = new int[n+1][2];
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            talk[i][0] = Integer.parseInt(st.nextToken());
            talk[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <=n; i++) {
            int endDay = talk[i][0] + i - 1;
            if(endDay <= n){
                for (int j = endDay+1; j <=n; j++) {
                    if(talk[j][0] + j <=n){
                        dp[j] = Math.max(dp[talk[i][0] + i - 1], dp[talk[i][0] + i - 1]+talk[i][1]);
                    }

                }
                

            }
        }
        
    }
    
}
