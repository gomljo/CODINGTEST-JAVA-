import java.util.*;
class Solution {
    public HashMap<Integer, List<Integer>> makeGraph(List<int[]> nodes) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] node : nodes) {
            graph.computeIfAbsent(node[0], key -> new ArrayList<>()).add(node[1]);
            graph.computeIfAbsent(node[1], key -> new ArrayList<>()).add(node[0]);
        }

        return graph;
    }

    public static int[] search(int[] visited, HashMap<Integer, List<Integer>> graph, int key) {
        List<Integer> keys = new ArrayList<>(graph.keySet());

        if(keys.contains(key)){
            visited[key-1] = 1;
            List<Integer> nodes = graph.get(key);
            for (int dest : nodes) {
                if (visited[dest-1] == 0) {
                    visited[dest-1] = 1;
                    visited = search(visited, graph, dest);
                }
            }
        }
        else {
            visited[key-1] = 1;
            return visited;
        }
        return visited;
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        List<int[]> nodes = new ArrayList<>(Arrays.asList(wires));
        List<Integer> diff = new ArrayList<>();
       for (int i = 0; i < nodes.size(); i++) {
            int[] cutNode = nodes.get(i);
            List<int[]> nodeList = new ArrayList<>(nodes.subList(0, i));
            nodeList.addAll(nodes.subList(i + 1, nodes.size()));
            HashMap<Integer, List<Integer>> graph = makeGraph(nodeList);
            int[] leftVisited = new int[n];
            int[] rightVisited = new int[n];
            search(leftVisited, graph, cutNode[0]);
            search(rightVisited, graph, cutNode[1]);
            int leftCount = (int) Arrays.stream(leftVisited).filter(visit -> visit == 1).count();
            int rightCount = (int) Arrays.stream(rightVisited).filter(visit -> visit == 1).count();
            diff.add(Math.abs(leftCount - rightCount));
        }
        diff.sort(Comparator.naturalOrder());
        return diff.get(0);
    }
}