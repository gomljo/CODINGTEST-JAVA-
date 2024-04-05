import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] distance = new int[n][n];
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    continue;
                }
                distance[i][j] = MAX;
            }


        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[start-1][end-1] = Math.min(cost, distance[start-1][end-1]);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(distance[i][j]==MAX){
                    sb.append("0 ");
                }
                else {
                    sb.append(distance[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);

        br.close();
    }
}