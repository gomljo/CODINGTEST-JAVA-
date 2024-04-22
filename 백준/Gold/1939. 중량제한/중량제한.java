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
        MaximumWeightFinder finder = new MaximumWeightFinder(graph);
        st = new StringTokenizer(br.readLine());
        int factory = Integer.parseInt(st.nextToken());
        int otherFactory = Integer.parseInt(st.nextToken());
        finder.calculate(factory, otherFactory);
        finder.printMaxWeight();
        br.close();
    }
}
class MaximumWeightFinder {
    private final List<List<Edge>> graph;
    private int maxWeight;

    public MaximumWeightFinder(List<List<Edge>> graph) {
        this.graph = graph;
        this.maxWeight = 0;
    }

    private boolean bfs(int weightLimit, int start, int end) {
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

    public void calculate(int factory, int otherFactory){
        int minimumWeight = 1;
        int maximumWeight = 1000000000;
        while (minimumWeight <= maximumWeight) {
            int mid = (minimumWeight+maximumWeight) / 2;

            if(bfs(mid, factory, otherFactory)){
                minimumWeight = mid+1;
            }
            else {
                maximumWeight = mid-1;
            }
        }
        this.maxWeight = maximumWeight;
    }

    public void printMaxWeight(){
        System.out.println(this.maxWeight);
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