import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int subin = scanner.nextInt();
        int sister = scanner.nextInt();

        boolean[] visited = new boolean[100_001];
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(subin, 0));

        while (!queue.isEmpty()) {

            Move current = queue.poll();

            if(current.getPosition()==sister){
                System.out.println(current.getCount());
                break;
            }

            if (visited[current.getPosition()]) {
                continue;
            }
            visited[current.getPosition()] = true;

            if (current.getPosition() - 1 >= 0) {
                queue.add(new Move(current.getPosition() - 1, current.getCount() + 1));
            }

            if (current.getPosition() + 1 <= 100_000) {
                queue.add(new Move(current.getPosition() + 1, current.getCount() + 1));
            }

            if (current.getPosition() * 2 <= 100_000) {
                queue.add(new Move(current.getPosition()*2, current.getCount()+1));
            }

        }
    }
}

class Move {
    private final int position;
    private final int count;

    public Move(int position, int count) {
        this.position = position;
        this.count = count;
    }

    public int getPosition() {
        return position;
    }

    public int getCount() {
        return count;
    }
}