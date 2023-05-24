import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parentNode = new int[N];

        // 트리 구성을 위한 리스트 초기화
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<Integer>());
        }

        // 트리 구성 완료
        for (int i = 0; i < N-1; i++) {
            String[] record = br.readLine().split(" ");
            int start = Integer.parseInt(record[0]) - 1;
            int end = Integer.parseInt(record[1]) - 1;
            tree.get(start).add(end);
            tree.get(end).add(start);
        }

        // 방문하면서 루트 노드 찾기
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int node : tree.get(v))
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                    parentNode[node] = v; // 부모 노드 찾은 후 저장
                }
        }

        // 루트 노드를 제외한 나머지 노드의 부모 노드 출력
        for (int i = 1; i < N; i++) {
            System.out.println(parentNode[i] + 1);
        }

    }

}
