import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberOfPeople = Integer.parseInt(st.nextToken());
        int numberOfRelationship = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= numberOfPeople; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numberOfRelationship; i++) {
            st = new StringTokenizer(br.readLine());
            int trusting = Integer.parseInt(st.nextToken());
            int trusted = Integer.parseInt(st.nextToken());
            graph.get(trusting).add(trusted);
        }
        Hacking hacking = new Hacking(graph, numberOfPeople);
        hacking.execute();
        hacking.findMax();
        hacking.printResult();

        br.close();
    }
}

class Hacking {
    private final List<List<Integer>> graph;
    private final int numberOfPeople;
    private int maximumHacking;
    private int[] numberOfHacking;

    public Hacking(List<List<Integer>> graph, int numberOfPeople) {
        this.graph = graph;
        this.numberOfPeople = numberOfPeople;
        maximumHacking = Integer.MIN_VALUE;
        this.numberOfHacking = new int[numberOfPeople + 1];
    }

    public void execute() {
        for (int i = 0; i < numberOfPeople; i++) {
            boolean[] visited = new boolean[this.numberOfPeople + 1];
            findPossibleToHack(i + 1, visited);
        }
    }

    private void findPossibleToHack(int person, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(person);
        visited[person] = true;
        while (!queue.isEmpty()) {

            Integer current = queue.poll();

            for (Integer next : this.graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    this.numberOfHacking[next]++;
                    queue.offer(next);
                }
            }

        }
    }

    public void findMax() {
        for (int j : this.numberOfHacking) {
            this.maximumHacking = Math.max(this.maximumHacking, j);
        }
    }

    public void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfHacking.length; i++) {
            if (this.numberOfHacking[i] == this.maximumHacking) {
                sb.append(i);
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}