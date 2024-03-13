import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 다익스트라?
    private static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfVertex = Integer.parseInt(st.nextToken());
        int numberOfEdge = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= numberOfVertex; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < numberOfEdge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, weight));
            graph.get(end).add(new Edge(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int first = 0;
        int second = 0;
        int third = 0;

        if (u == 1 && v == numberOfVertex) {
            first = findShortestPath(1, numberOfVertex, numberOfVertex);
        } else if (v == numberOfVertex) {
            first = findShortestPath(1, u, numberOfVertex);
            second = findShortestPath(u, numberOfVertex, numberOfVertex);
        } else if (u == 1) {
            second = findShortestPath(u, v, numberOfVertex);
            third = findShortestPath(v, numberOfVertex, numberOfVertex);
        } else {
            second = findShortestPath(u, v, numberOfVertex);
            int[] startToU = startTo(u, v, numberOfVertex);
            int[] startToV = startTo(v, u, numberOfVertex);
            if(startToU[2] > startToV[2]){
                first = startToV[0];
                third = startToV[1];
            }
            else {
                first = startToU[0];
                third = startToU[1];
            }
        }
        if(first==Integer.MAX_VALUE || second==Integer.MAX_VALUE || third==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else {
            System.out.println(first + second + third);

        }

        br.close();
    }

    public static int[] startTo(int u, int v, int n) {
        int first = findShortestPath(1, u, n);
        int third = findShortestPath(v, n, n);
        return new int[]{first, third, first+third};
    }

    public static int findShortestPath(int start, int end, int numberOfNode) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        int[] cost = new int[numberOfNode + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (cost[current.getEnd()] < current.getWeight()) {
                continue;
            }

            List<Edge> adjacentEdge = graph.get(current.getEnd());
            for (Edge edge : adjacentEdge) {
                if (edge.getWeight() + current.getWeight() < cost[edge.getEnd()]) {
                    cost[edge.getEnd()] = edge.getWeight() + current.getWeight();
                    pq.offer(new Edge(edge.getEnd(), cost[edge.getEnd()]));
                }
            }
        }
        return cost[end];
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