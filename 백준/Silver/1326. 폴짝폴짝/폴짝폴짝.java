import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bridge = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bridge[i] = sc.nextInt();
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        int count = Integer.MAX_VALUE;

        Queue<Frog> frogQueue = new LinkedList<>();
        frogQueue.add(new Frog(start, bridge[start], 0));
        boolean[] visited = new boolean[n + 1];
        while (!frogQueue.isEmpty()) {
            Frog current = frogQueue.poll();
            int move = current.getMove();
            int next = current.getPosition();

            while (true) {
                next += move;
                if (next > n) {
                    break;
                }
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;

                if (next == end) {
                    System.out.println(current.getCount() + 1);
                    return;
                }
                frogQueue.add(new Frog(next, bridge[next], current.getCount() + 1));
            }

            next = current.getPosition();

            while (true) {
                next -= move;

                if (next < 1) {
                    break;
                }
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;

                if (next == end) {
                    System.out.println(current.getCount() + 1);
                    return;
                }
                frogQueue.add(new Frog(next, bridge[next], current.getCount() + 1));
            }


        }
        System.out.println(-1);
        sc.close();
    }
}

class Frog {
    private int position;
    private int move;
    private int count;

    public Frog(int current, int move, int count) {
        this.position = current;
        this.move = move;
        this.count = count;
    }

    public int getPosition() {
        return position;
    }

    public int getMove() {
        return move;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Frog{" +
                "position=" + position +
                ", move=" + move +
                ", count=" + count +
                '}';
    }
}