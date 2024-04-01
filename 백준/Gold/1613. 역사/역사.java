import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_VALUE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] distance = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            distance[start][end] = -1;
            distance[end][start] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    if(distance[j][i]==1 && distance[i][l]==1){
                        distance[j][l] = 1;
                    } else if (distance[j][i]==-1 && distance[i][l]==-1) {
                        distance[j][l] = -1;
                    }
                }
            }
        }
        int numberOfQuery = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfQuery; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(distance[s][e]!= MAX_VALUE){
                System.out.println(distance[s][e]);
            }
            else {
                System.out.println(0);
            }
        }
        br.close();
    }
}

class Edge {
    private final int end;
    private final int weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}