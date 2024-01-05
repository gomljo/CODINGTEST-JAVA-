import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        List<List<Edge>> connections = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            connections.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            connections.get(start).add(new Edge(end, weight));
            connections.get(end).add(new Edge(start, weight));
        }
        int cost = 0;
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        edges.offer(new Edge(1, 0));
        boolean[] visited = new boolean[V+1];
        while (!edges.isEmpty()){
            Edge edge = edges.poll();

            if(visited[edge.getEnd()]){
                continue;
            }
            visited[edge.getEnd()] = true;
            cost += edge.getWeight();
            List<Edge> adjacentEdgeList = connections.get(edge.getEnd());

            for (Edge adjacentEdge: adjacentEdgeList) {
                if(!visited[adjacentEdge.getEnd()]){
                    edges.offer(adjacentEdge);
                }
            }
            
        }
        System.out.println(cost);
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