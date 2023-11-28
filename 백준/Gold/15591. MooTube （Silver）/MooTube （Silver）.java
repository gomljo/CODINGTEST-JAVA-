import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int q = Integer.parseInt(params[1]);
        HashMap<Integer, List<Node>> map = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            int start = Integer.parseInt(nodeInfo[0]);
            int end = Integer.parseInt(nodeInfo[1]);
            int weight = Integer.parseInt(nodeInfo[2]);
            map.computeIfAbsent(start, key-> new ArrayList<Node>()).add(new Node(end, weight));
            map.computeIfAbsent(end, key->new ArrayList<Node>()).add(new Node(start, weight));
        }
        for (int key: map.keySet()) {
            map.get(key).sort(Comparator.comparing(Node::getWeight));
        }

        for (int i = 0; i < q; i++) {
            String[] question = br.readLine().split(" ");
            int k = Integer.parseInt(question[0]);
            int point = Integer.parseInt(question[1]);
            boolean[] visited = new boolean[map.keySet().size()];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(point);
            int cnt = 0;
            while (!queue.isEmpty()){

                int index = queue.poll();
                visited[index-1] = true;
                List<Node> nodeList = map.get(index);
                for(Node node: nodeList){
                    if (!visited[node.getDest()-1] && node.getWeight() >= k){
                       cnt++;
                       queue.add(node.getDest());
                       visited[node.getDest()-1] = true;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
class Node {
    private int dest;
    private int weight;

    public Node(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}