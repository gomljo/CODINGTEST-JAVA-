import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfCity = Integer.parseInt(br.readLine());
        int numberOfBus = Integer.parseInt(br.readLine());
        List<List<Edge>> graph = new LinkedList<>();
        for (int i = 0; i <= numberOfCity; i++) {
            graph.add(new LinkedList<>());
        }
        StringTokenizer st;

        for (int i = 0; i < numberOfBus; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(start, end, cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        List<Edge> adjacentNodeFromStart = graph.get(start);
        adjacentNodeFromStart.sort(Comparator.comparing(Edge::getCost));
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        pq.offer(new Edge(0, start, 0));
        int[] cost = new int[numberOfCity + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (cost[current.getEnd()] < current.getCost()) {
                continue;
            }
            List<Edge> edges = graph.get(current.getEnd());
            for (Edge edge : edges) {
                if (cost[edge.getEnd()] > current.getCost() + edge.getCost()) {
                    cost[edge.getEnd()] = current.getCost() + edge.getCost();
                    pq.offer(new Edge(edge.getStart(), edge.getEnd(), cost[edge.getEnd()]));
                }
            }
        }
        System.out.println(cost[end]);
    }
}

class Edge {
    private final int start;
    private final int end;
    private final int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCost() {
        return cost;
    }
}