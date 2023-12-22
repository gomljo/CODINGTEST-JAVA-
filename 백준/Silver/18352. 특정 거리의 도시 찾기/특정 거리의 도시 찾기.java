import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");

        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);
        int K = Integer.parseInt(params[2]);
        int X = Integer.parseInt(params[3]);

        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] connection = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).mapToInt(node -> node).toArray();
            nodes.get(connection[0]).add(connection[1]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new int[]{X, 0});
        boolean[] visited =new boolean[N+1];
        visited[0] = true;
        visited[1] = true;
        while (!pq.isEmpty()){
            int[] node = pq.poll();
            List<Integer> adjacentNodes = nodes.get(node[0]);
            for (int adjacentNode: adjacentNodes) {
                if(!visited[adjacentNode] && node[1]+1 < dist[adjacentNode]){
                    dist[adjacentNode] = node[1]+1;
                    pq.offer(new int[]{adjacentNode, node[1] + 1});
                }
            }
        }

        List<Integer> targetNodes = new ArrayList<>();
        for (int i = 0; i < dist.length; i++) {
            if(dist[i]==K){
                targetNodes.add(i);
            }
        }

        if(targetNodes.size()==0){
            System.out.println(-1);
        }
        else {
            targetNodes.sort(Comparator.naturalOrder());
            for(int targetNode: targetNodes){
                System.out.println(targetNode);
            }
        }
    }
}