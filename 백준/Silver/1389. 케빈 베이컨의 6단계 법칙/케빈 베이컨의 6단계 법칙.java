import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dist = new int[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                dist[i][j] = 100_000_000;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        PriorityQueue<Result> resultPriorityQueue = new PriorityQueue<>(Comparator.comparing(Result::getKevinBaconScore).thenComparing(Result::getIndex));

        for (int i = 0; i <= n; i++) {
            int score = Arrays.stream(dist[i]).sum();
            resultPriorityQueue.offer(new Result(i,score));
        }
        if(!resultPriorityQueue.isEmpty()){
            System.out.println(resultPriorityQueue.peek().getIndex());
        }
        br.close();

    }

}

class Result {
    private final int index;
    private final int kevinBaconScore;

    public Result(int index, int kevinBaconScore) {
        this.index = index;
        this.kevinBaconScore = kevinBaconScore;
    }

    public int getIndex() {
        return index;
    }

    public int getKevinBaconScore() {
        return kevinBaconScore;
    }
}

class Node {
    private int index;
    private int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public int getCost() {
        return cost;
    }
}