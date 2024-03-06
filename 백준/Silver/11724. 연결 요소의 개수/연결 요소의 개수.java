import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[] visited;
    private static List<List<Integer>> graph;

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
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        visited = new boolean[numberOfVertex + 1];
        int answer = 0;
        for (int i = 1; i <= numberOfVertex; i++) {
            if (!visited[i]) {
                findConnectedComponent(i);
                answer++;
            }
        }
        System.out.println(answer);
        br.close();
    }

    public static void findConnectedComponent(int startNode) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startNode, 0));
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (visited[currentNode.getEnd()]) {
                continue;
            }
            visited[currentNode.getEnd()] = true;
            List<Integer> adjacentNodeList = graph.get(currentNode.getEnd());
            for (Integer adjacentNode : adjacentNodeList) {
                if (!visited[adjacentNode]) {
                    queue.add(new Node(adjacentNode, currentNode.getSize() + 1));
                }
            }
        }
    }
}

class Node {
    private int end;
    private int size;

    public Node(int end, int size) {
        this.end = end;
        this.size = size;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }
}