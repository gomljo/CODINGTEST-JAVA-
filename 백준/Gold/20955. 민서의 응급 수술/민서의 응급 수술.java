import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] parent = new int[n+1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int pa = find(a, parent);
            int pb = find(b, parent);
            if (pa == pb) {
                count++;
                continue;
            }

            if (pa > pb) {
                parent[pa] = pb;
            } else {
                parent[pb] = pa;
            }
        }
        for (int i = 1; i <=n ; i++) {
            find(i, parent);
        }
        Set<Integer> parentSet = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            parentSet.add(parent[i]);
        }
        System.out.println(parentSet.size() - 2 + count);

        br.close();
    }

    public static int find(int a, int[] parent) {
        if (parent[a] != a) {
            parent[a] = find(parent[a], parent);
        }
        return parent[a];
    }
}