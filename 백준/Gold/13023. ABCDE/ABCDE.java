import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void breadthFirstSearch(Node node, List<List<Integer>> coordinates, boolean[] visited, List<Integer> result) {

        if (node.getDepth() == 5) {
            result.add(1);
            return;
        }
        visited[node.getNodeIndex()] = true;
        List<Integer> adjacentNodeIndexes = coordinates.get(node.getNodeIndex());
        for (Integer adjacentNodeIndex : adjacentNodeIndexes) {
            if (!visited[adjacentNodeIndex]) {
                breadthFirstSearch(new Node(adjacentNodeIndex, node.getDepth() + 1), coordinates, visited, result);
            }
        }
        visited[node.getNodeIndex()] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int numberOfPeople = Integer.parseInt(params[0]);
        int numberOfRelation = Integer.parseInt(params[1]);
        List<List<Integer>> graph = new LinkedList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < numberOfRelation; i++) {
            int[] coordinate = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .mapToInt(c -> c)
                    .toArray();
            graph.get(coordinate[0]).add(coordinate[1]);
            graph.get(coordinate[1]).add(coordinate[0]);
        }
        int answer = 0;
        for (int i = 0; i < numberOfPeople; i++) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[numberOfPeople];
            breadthFirstSearch(new Node(i, 1), graph, visited, result);
            if (result.stream().anyMatch(r -> r == 1)) {
                answer = 1;
                break;
            }
        }
        System.out.println(answer);
    }
}

class Node {
    private final int nodeIndex;
    private final int depth;

    public Node(int nodeIndex, int depth) {
        this.nodeIndex = nodeIndex;
        this.depth = depth;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public int getDepth() {
        return depth;
    }
}