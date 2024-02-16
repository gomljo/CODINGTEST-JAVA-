import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 최소 스패닝 트리인데 성별을 번갈아가면서 연결되야한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String[] gender = new String[n];
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        boolean[] isWomen = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            gender[i] = st.nextToken();
            if (gender[i].equals("W")) {
                isWomen[i + 1] = true;
            }
        }
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (isWomen[start] != isWomen[end]) {
                edgeList.add(new Edge(start, end, weight));
            }
        }
        edgeList.sort(Comparator.comparing(Edge::getWeight));
        int answer = 0;
        int cnt = 0;
        for (Edge edge: edgeList) {
            if(find(parent, edge.getStart())!=find(parent, edge.getEnd())){
                union(parent, edge.getStart(), edge.getEnd());
                answer += edge.getWeight();
                cnt++;
            }
            if(cnt == n-1){
                break;
            }
        }

        if(cnt != n-1){
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }

        br.close();
    }

    public static void union(int[] parent, int start, int end) {
        int aParent = find(parent, start);
        int bParent = find(parent, end);
        if (aParent < bParent) {
            parent[aParent] = bParent;
        }
        else {
            parent[bParent] = aParent;
        }
    }

    public static int find(int[] parent, int node) {
        if (node == parent[node]) {
            return node;
        }
        parent[node] = find(parent, parent[node]);
        return parent[node];
    }
}

class Edge {
    private int start;
    private int end;
    private int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}