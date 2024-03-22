import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Edge(e, w));
            graph.get(e).add(new Edge(s, w));
        }
        st = new StringTokenizer(br.readLine());
        int factory = Integer.parseInt(st.nextToken());
        int otherFactory = Integer.parseInt(st.nextToken());
        int start = 1;
        int end = 1000000000;
        while (start <= end) {
            int mid = (start+end) / 2;

            if(bfs(mid, factory, otherFactory)){
                start = mid+1;
            }
            else {
                end = mid-1;
            }
        }
        System.out.println(end);
        br.close();
    }

    public static boolean bfs(int weightLimit, int start, int end) {
        Queue<Edge> queue = new LinkedList<>();
        queue.add(new Edge(start, Integer.MIN_VALUE));
        boolean[] visited = new boolean[graph.size()+1];
        visited[start] = true;
        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            List<Edge> adjacent = graph.get(current.getEnd());

            for (Edge edge : adjacent) {
                if (!visited[edge.getEnd()] && edge.getWeight() >= weightLimit) {
                    visited[edge.getEnd()] = true;
                    queue.offer(new Edge(edge.getEnd(), edge.getWeight()));
                }

            }
        }
        return visited[end];
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

    @Override
    public String toString() {
        return "Edge{" +
                "end=" + end +
                ", weight=" + weight +
                '}';
    }
}