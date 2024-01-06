import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfClasses = Integer.parseInt(br.readLine());
        PriorityQueue<Class> priorityQueue = new PriorityQueue<>(Comparator.comparing(Class::getStart));
        for (int i = 0; i < numberOfClasses; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            priorityQueue.add(new Class(start, end));
        }
        PriorityQueue<Class> pq = new PriorityQueue<>(Comparator.comparing(Class::getEnd));
        while (!priorityQueue.isEmpty()) {
            Class aClass = priorityQueue.poll();

            if (pq.isEmpty()) {
                pq.offer(aClass);
                continue;
            }

            if (pq.peek().getEnd() <= aClass.getStart()) {
                pq.poll();
            }
            pq.offer(aClass);
        }
        System.out.println(pq.size());

    }
}

class Class {
    private final int start;
    private final int end;

    public Class(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}