import java.util.*;

public class Main {
    private static final int RED = 1;
    private static final int BLACK = -1;
    private static List<List<Integer>> graphs;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCase = scanner.nextInt();
        while (numberOfTestCase > 0) {

            int V = scanner.nextInt();
            int E = scanner.nextInt();

            graphs = new ArrayList<>();
            int[] color = new int[V + 1];

            for (int i = 0; i <= V; i++) {
                graphs.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                graphs.get(start).add(end);
                graphs.get(end).add(start);
            }

            boolean isBipartiteGraph = true;
            for (int i = 1; i <= V; i++) {
                if (!isBipartiteGraph) {
                    break;
                }

                if (color[i] == 0) {
                    isBipartiteGraph = bfs(i, color);
                }
            }
            if (isBipartiteGraph) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            numberOfTestCase--;
        }
        scanner.close();
    }

    public static boolean bfs(int index, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        color[index] = RED;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int adjacent : graphs.get(current)) {
                if (color[adjacent] == 0) {
                    color[adjacent] = color[current] * -1;
                    queue.offer(adjacent);
                } else if (color[adjacent] == color[current]) {
                    return false;
                }
            }
        }
        return true;
    }

}