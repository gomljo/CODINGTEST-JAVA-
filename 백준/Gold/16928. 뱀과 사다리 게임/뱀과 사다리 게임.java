import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private final static int[] DICE = new int[]{1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> ladder = new HashMap<>();
        HashMap<Integer, Integer> snake = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        boolean[] visited = new boolean[101];
        Queue<Player> queue = new LinkedList<>();
        queue.offer(new Player(1, 0));
        visited[1] = true;
        int count = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Player player = queue.poll();
            if (player.getPosition() == 100) {
                count = Math.min(count, player.getCount());
                break;
            }
            for (int d : DICE) {
                int nextPos = player.getPosition() + d;
                if (nextPos <= 100 && !visited[nextPos]) {
                    if(ladder.containsKey(nextPos)){
                        visited[ladder.get(nextPos)]= true;
                        queue.offer(new Player(ladder.get(nextPos), player.getCount()+1));
                    } else if (snake.containsKey(nextPos)){
                        visited[snake.get(nextPos)]= true;
                        queue.offer(new Player(snake.get(nextPos), player.getCount()+1));
                    }
                    else {
                        visited[nextPos]= true;
                        queue.offer(new Player(nextPos, player.getCount() + 1));
                    }
                }
            }
        }
        System.out.println(count);
        br.close();
    }
}

class Player {
    private final int position;
    private final int count;

    public Player(int position, int count) {
        this.position = position;
        this.count = count;
    }

    public int getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                ", count=" + count +
                '}';
    }
}