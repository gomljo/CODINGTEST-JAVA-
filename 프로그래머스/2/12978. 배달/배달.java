import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // bfs로 풀면 될거 같은데?

        List<List<Connection>> connectionList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            connectionList.add(new ArrayList<Connection>());
        }

        for (int[] r : road) {
            List<Connection> start = connectionList.get(r[0]);
            start.add(new Connection(r[1], r[2]));
            List<Connection> end = connectionList.get(r[1]);
            end.add(new Connection(r[0], r[2]));
        }

        for (List<Connection> connections : connectionList) {
            Collections.sort(connections);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int[] start = queue.poll();

            List<Connection> nextNodes = connectionList.get(start[0]);
            for (Connection nextNode : nextNodes) {
                if (start[1] + nextNode.getTime() <= dist[nextNode.getDestination()-1]) {
                    dist[nextNode.getDestination()-1] = start[1] + nextNode.getTime();
                    queue.add(new int[]{nextNode.getDestination(), start[1] + nextNode.getTime()});
                }
            }
        }
        return (int) Arrays.stream(dist).filter(d -> d <= K).count();
    }
}

class Connection implements Comparable<Connection> {
    private final int destination;
    private final int time;

    public Connection(int destination, int time) {
        this.destination = destination;
        this.time = time;
    }

    public int getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "destination=" + destination +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(Connection connection) {
        return this.time - connection.getTime();
    }
}
