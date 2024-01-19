import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void print(int[] candidates, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.printf("%d ", candidates[i]);
            }
        }
        System.out.print("\n");
    }

    public static void combination(int start, int n, int k, int[] candidates, String cases, boolean[] visited) {

        if (k == 0) {
            print(candidates, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(i + 1, n, k - 1, candidates, cases + candidates[i] + " ", visited);
            visited[i] = false;

        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.countTokens() > 1) {
            int n = Integer.parseInt(st.nextToken());
            int[] candidates = new int[n];
            for (int i = 0; i < n; i++) {
                candidates[i] = Integer.parseInt(st.nextToken());
            }
            boolean[] visited = new boolean[n];
            combination(0, n, 6, candidates, "", visited);
            System.out.println();
            st = new StringTokenizer(br.readLine());
        }

    }
}