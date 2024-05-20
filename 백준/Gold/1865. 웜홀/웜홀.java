import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dist;

    // 벨만 포드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestcases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestcases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numberOfNode = Integer.parseInt(st.nextToken());
            int numberOfRoad = Integer.parseInt(st.nextToken());
            int numberOfWarmHole = Integer.parseInt(st.nextToken());

            dist = new int[numberOfNode + 1];
            List<List<Edge>> graph = new ArrayList<>();
            for (int j = 0; j <= numberOfNode; j++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < numberOfRoad; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Edge(start, end, time));
                graph.get(end).add(new Edge(end, start, time));
            }
            for (int j = 0; j < numberOfWarmHole; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Edge(start, end, time * -1));
            }
            sb.append(bellmanFord(numberOfNode, graph) ? "YES\n" : "NO\n");

        }
        System.out.println(sb);

        br.close();
    }

    public static boolean bellmanFord(int N, List<List<Edge>> graph) {
        Arrays.fill(dist, 987654321);
        dist[1] = 0; // 시작점은 0으로 초기화.
        boolean update = false;

        // (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복함.
        for (int i = 1; i < N; i++) {
            update = false;

            // 최단거리 초기화.
            for (int j = 1; j <= N; j++) {
                for (Edge road : graph.get(j)) {
                    if (dist[road.getEnd()] > dist[j] + road.getTime()) {
                        dist[road.getEnd()] = dist[j] + road.getTime();
                        update = true;
                    }
                }
            }

            // 더 이상 최단거리 초기화가 일어나지 않았을 경우 반복문을 종료.
            if (!update) {
                break;
            }
        }

        // (정점의 개수 - 1)번까지 계속 업데이트가 발생했을 경우
        // (정점의 개수)번도 업데이트 발생하면 음수 사이클이 일어난 것을 의미함.
        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Edge road : graph.get(i)) {
                    if (dist[road.getEnd()] > dist[i] + road.getTime()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

class Edge {
    private final int start;
    private final int end;
    private final int time;

    public Edge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}