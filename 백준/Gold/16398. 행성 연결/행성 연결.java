import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 최소 신장 트리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Node>> nodeList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                nodeList.get(i+1).add(new Node(j+1, cost));
                nodeList.get(j+1).add(new Node(i+1, cost));
            }
        }
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1,0));
        long answer = 0;
        while (!queue.isEmpty()){

            Node node = queue.poll();
            int to = node.getTo();
            int cost = node.getCost();

            if(visited[to]){
                continue;
            }
            visited[to] =true;
            answer += cost;

            for (Node next : nodeList.get(to)) {
                if(!visited[next.getTo()]){
                    queue.offer(next);
                }
            }

        }
        System.out.println(answer);
        br.close();
    }
}
class Node implements Comparable<Node>{
    private final int to;
    private final int cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
    }
}