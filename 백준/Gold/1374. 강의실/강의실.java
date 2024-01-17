import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> lectures = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.offer(new Lecture(start, end));
        }
        PriorityQueue<Integer> room = new PriorityQueue<>(Comparator.naturalOrder());
        while (!lectures.isEmpty()){
            Lecture lecture = lectures.poll();
            if(!room.isEmpty() && room.peek() <= lecture.getStart()){
                room.poll();
                room.offer(lecture.getEnd());
            }
            else {
                room.offer(lecture.getEnd());
            }
        }
        System.out.println(room.size());

    }
}

class Lecture implements Comparable<Lecture> {
    private int start;
    private int end;

    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Lecture otherLecture) {
        if(this.start== otherLecture.start){
            return this.end - otherLecture.end;
        }
        return this.start - otherLecture.start;
    }
}