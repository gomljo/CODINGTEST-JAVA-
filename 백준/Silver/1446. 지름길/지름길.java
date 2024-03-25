import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfPath = Integer.parseInt(st.nextToken());
        int highwayLength = Integer.parseInt(st.nextToken());
        List<List<Path>> graph = new ArrayList<>();

        for (int i = 0; i < 10002; i++) {
            List<Path> paths = new ArrayList<>();
            paths.add(new Path(i + 1, 1));
            graph.add(paths);
        }

        for (int i = 0; i < numberOfPath; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Path(end, length));
        }

        int[] dp = new int[10001];
        for (int i = 0; i < 10001; i++) {
            dp[i] = i;
        }
        PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparing(Path::getEnd).reversed().thenComparing(Path::getLength));
        pq.offer(new Path(0, 0));
        while (!pq.isEmpty()) {
            Path current = pq.poll();

            if(current.getEnd() > highwayLength){
                continue;
            }
            if (dp[current.getEnd()] < current.getLength()) {
                continue;
            }
            List<Path> paths = graph.get(current.getEnd());
            for (Path path: paths) {
                if(current.getLength()+path.getLength() <= dp[path.getEnd()]){
                    dp[path.getEnd()] = current.getLength()+path.getLength();
                    pq.offer(new Path(path.getEnd(), dp[path.getEnd()]));
                }
            }
        }
        System.out.println(dp[highwayLength]);

        br.close();
    }
}

class Path {
    private final int end;
    private final int length;

    public Path(int end, int length) {
        this.end = end;
        this.length = length;
    }


    public int getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Path{" +
                "end=" + end +
                ", length=" + length +
                '}';
    }
}