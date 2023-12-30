import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] params = bufferedReader.readLine().split(" ");

        int N = Integer.parseInt(params[0]);
        int K = Integer.parseInt(params[1]);

        Queue<Finder> queue = new LinkedList<>();
        boolean[] visited = new boolean[100_000+1];
        queue.offer(new Finder(N, 0));
        while (!queue.isEmpty()) {

            Finder current = queue.poll();
            visited[current.getIndex()] = true;
            if (current.getIndex() == K) {
                min = Math.min(min, current.getTime());
            }

            if (current.getIndex() * 2 <= 100000 && !visited[current.getIndex()*2]) {
                queue.offer(new Finder(current.getIndex() * 2, current.getTime()));
            }
            if (current.getIndex() + 1 <= 100000 && !visited[current.getIndex() + 1]) {
                queue.offer(new Finder(current.getIndex() + 1, current.getTime()+1));
            }
            if (current.getIndex() - 1 >= 0 && !visited[current.getIndex() - 1]) {
                queue.offer(new Finder(current.getIndex() - 1, current.getTime()+1));
            }
        }
        System.out.println(min);
    }
}

class Finder {
    private final int index;
    private final int time;

    public Finder(int index, int time) {
        this.index = index;
        this.time = time;
    }

    public int getIndex() {
        return index;
    }

    public int getTime() {
        return time;
    }
}