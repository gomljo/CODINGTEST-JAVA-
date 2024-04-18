import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 위상 정렬이란다...
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int[] time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            int[] entrance = new int[vertex];
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i <= vertex; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < edge; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                entrance[end - 1]++;
                graph.get(start).add(end);
            }
            int target = Integer.parseInt(br.readLine());
            int res = TopologicalSort(entrance, time, graph, target);
            sb.append(res).append("\n");
            n--;
        }
        System.out.println(sb);

        br.close();
    }

    public static int TopologicalSort(int[] entrance, int[] time, List<List<Integer>> graph, int target) {
        Queue<Node> queue = new LinkedList<>();
        int[] dp = new int[1001];
        int pathIndex = 0;
        for (int i = 0; i < entrance.length; i++) {
            if (entrance[i] == 0) {
                queue.offer(new Node(i + 1, 1, pathIndex++));
                dp[i + 1] = time[i];
            }
        }
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int index : graph.get(current.getIndex())) {
                entrance[index - 1]--;

                if (dp[current.getIndex()] + time[index - 1] > dp[index]) {
                    dp[index] = dp[current.getIndex()] + time[index - 1];
                }
                if (entrance[index - 1] == 0) {
                    queue.offer(new Node(index, current.getLevel() + 1, current.getPathIndex()));
                }
            }
        }
        return dp[target];
    }
}

class Node {
    private final int index;
    private final int level;

    public int getPathIndex() {
        return pathIndex;
    }

    private final int pathIndex;

    public Node(int index, int level, int pathIndex) {
        this.index = index;
        this.level = level;
        this.pathIndex = pathIndex;
    }

    public int getIndex() {
        return index;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", level=" + level +
                ", pathIndex=" + pathIndex +
                '}';
    }
}