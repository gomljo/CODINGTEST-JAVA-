import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTest = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfTest; i++) {
            int numberOfNode = Integer.parseInt(br.readLine());
            int[] tree = new int[numberOfNode + 1];
            StringTokenizer st;
            for (int j = 0; j < numberOfNode - 1; j++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                tree[child] = parent;
            }
            st = new StringTokenizer(br.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            List<Integer> parent1 = new ArrayList<>();
            parent1.add(firstNode);
            parent1 = extractParent(parent1, tree, firstNode);
            int secondNode = Integer.parseInt(st.nextToken());
            List<Integer> parent2 = new ArrayList<>();
            parent2.add(secondNode);
            parent2 = extractParent(parent2, tree, secondNode);
            boolean isFind = false;
            for (int j = 0; j < parent1.size(); j++) {
                for (int k = 0; k < parent2.size(); k++) {
                    if (Objects.equals(parent1.get(j), parent2.get(k))) {
                        System.out.println(parent2.get(k));
                        isFind = true;
                        break;
                    }
                }
                if (isFind) {
                    break;
                }
            }
        }

        br.close();
    }

    public static List<Integer> extractParent(List<Integer> parents, int[] tree, int node) {
        if (tree[node] == 0) {
            return parents;
        }
        parents.add(tree[node]);
        return extractParent(parents, tree, tree[node]);
    }
}