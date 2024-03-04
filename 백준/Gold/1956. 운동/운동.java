import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 551483647;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        // 비용 비교도 해야하지 않을까? => 비용 배열을 만듦
        long[][] distance = new long[v + 1][v + 1];
        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], INF);
        }
        // 비용을 초기화
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            distance[start][end] = Math.min(distance[start][end], weight);
        }

        // 비용 비교
        for (int nodeNumber = 1; nodeNumber <= v; nodeNumber++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if(distance[nodeNumber][i] > distance[nodeNumber][j] + distance[j][i]){
                        distance[nodeNumber][i] = distance[nodeNumber][j] + distance[j][i];
                    }
                }
            }
        }
        long answer = Long.MAX_VALUE;
        // 사이클이 생성되는 조건?
        for (int i = 1; i <= v; i++) {
            for (int j = i+1; j <= v; j++) {
                if(distance[i][j] != INF && distance[j][i] != INF){
                    answer = Math.min(answer, distance[i][j] + distance[j][i]);
                }
            }
        }
        if (answer == Long.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }

        br.close();
    }
}