import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfHomework;
        List<Homework> homeworkList = new ArrayList<>();
        StringTokenizer st;
        int lastDay = 0;
        try {
            numberOfHomework = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberOfHomework; i++) {
                st = new StringTokenizer(br.readLine());
                int deadLine = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());
                homeworkList.add(new Homework(deadLine, score));
                lastDay = Math.max(deadLine, lastDay);
            }
            homeworkList.sort(Comparator.comparing(Homework::getDeadLine).reversed());
            int totalScore = 0;
            PriorityQueue<Homework> pq = new PriorityQueue<>(Comparator.comparing(Homework::getScore).reversed());
            int index = 0;
            for (int i = lastDay; i > 0; i--) {
                while (index < numberOfHomework && homeworkList.get(index).getDeadLine() >= i) {
                    pq.offer(homeworkList.get(index));
                    index+=1;
                }
                if(!pq.isEmpty()){
                    totalScore += pq.poll().getScore();
                }
            }

            System.out.println(totalScore);

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

}

class Homework {
    private final int deadLine;
    private final int score;

    public Homework(int deadLine, int score) {
        this.deadLine = deadLine;
        this.score = score;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "deadLine=" + deadLine +
                ", score=" + score +
                '}';
    }
}