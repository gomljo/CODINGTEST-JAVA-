import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int numberOfVertex = Integer.parseInt(params[0]);
        int numberOfEdges = Integer.parseInt(params[1]);
        int start = Integer.parseInt(br.readLine());

        List<List<Node>> connectionList = new ArrayList<>();

        // 노드 연결 정보 초기화
        for (int i = 0; i <= numberOfVertex; i++) {
            connectionList.add(new ArrayList<>());
        }

        // 노드 연결 정보 수집
        for (int i = 0; i < numberOfEdges; i++) {
            int[] connection = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .mapToInt(c -> c)
                    .toArray();
            connectionList.get(connection[0]).add(new Node(connection[1], connection[2]));
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        priorityQueue.add(new Node(start, 0));
        int[] cost = new int[numberOfVertex + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        boolean[] visited = new boolean[numberOfVertex + 1];

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            visited[currentNode.getEnd()] = true;
            List<Node> adjacentNodes = connectionList.get(currentNode.getEnd());
            for (Node node : adjacentNodes) {
                if (cost[node.getEnd()] > cost[currentNode.getEnd()] + node.getWeight() && !visited[node.getEnd()]) {
                    cost[node.getEnd()] = cost[currentNode.getEnd()] + node.getWeight();
                    priorityQueue.add(new Node(node.getEnd(), cost[node.getEnd()]));
                }
            }
        }

        for (int i = 1; i < cost.length; i++) {
            if (cost[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(cost[i]);
        }
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
}