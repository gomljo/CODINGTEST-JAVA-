import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfStudent = Integer.parseInt(st.nextToken());
        int numberOfRoad = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < numberOfStudent + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numberOfRoad; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }
        int answer = 0;
        int[] destDist = calculateMinDist(destination, graph, numberOfStudent);
        for (int i = 1; i <= numberOfStudent; i++) {
            int[] eachDist = calculateMinDist(i, graph, numberOfStudent);
            answer = Math.max(answer, destDist[i] + eachDist[destination]);
        }
        System.out.println(answer);


    }

    public static int[] calculateMinDist(int start, List<List<Node>> graph, int numberOfStudent) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getWeight));
        int[] dist = new int[numberOfStudent + 1]; // 최소 비용을 저장할 배열
        for (int i = 0; i < numberOfStudent + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {

            Node current = pq.poll();
            if (dist[current.getEnd()] < current.getWeight()) {
                continue;
            }
            for (Node node : graph.get(current.getEnd())) {
                if (dist[node.getEnd()] > current.getWeight() + node.getWeight()) {
                    dist[node.getEnd()] = current.getWeight() + node.getWeight();
                    pq.offer(new Node(node.getEnd(), dist[node.getEnd()]));
                }
            }
        }
        return dist;
    }
}

class Node {
    private final int end;
    private final int weight;

    public Node(int end, int weight) {
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
        return "Node{" +
                "end=" + end +
                ", weight=" + weight +
                '}';
    }
}