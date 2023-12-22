import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");

        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);

        List<List<Connection>> connectionList = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            connectionList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] connection = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).mapToInt(param -> param).toArray();
            connectionList.get(connection[0]).add(new Connection(connection[1], connection[2]));
            connectionList.get(connection[1]).add(new Connection(connection[0], connection[2]));
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Connection> pq = new PriorityQueue<>(new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                return o1.getCost() - o2.getCost();
            }
        });
        pq.add(new Connection(1, 0));
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        while (!pq.isEmpty()) {
            Connection current = pq.poll();
            visited[current.getDestination()] = true;
            List<Connection> adjacentConnections = connectionList.get(current.getDestination());

            for (Connection adjacent : adjacentConnections) {
                if (!visited[adjacent.getDestination()] && current.getCost() + adjacent.getCost() < dist[adjacent.getDestination()]) {

                    int updatedCost = current.getCost() + adjacent.getCost();
                    dist[adjacent.getDestination()] = updatedCost;
                    pq.add(new Connection(adjacent.getDestination(), updatedCost));
                }
            }
        }

        System.out.println(dist[N]);
    }
}

class Connection {
    private final int destination;
    private final int cost;

    public Connection(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }

    public int getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }
}