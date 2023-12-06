import java.util.*;

public class Solution {
    public static int parseTime(String time){
        int[] minuteAndSecond = Arrays.stream(time.split(":"))
                .map(Integer::valueOf)
                .mapToInt(unit -> unit)
                .toArray();

        return minuteAndSecond[0] * 60 + minuteAndSecond[1];
    }
    public int solution(String[][] book_time) {
        int answer = 0;

        PriorityQueue<Reservation> priorityQueue = new PriorityQueue<>(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                if(o1.getStart() < o2.getStart()){
                    return -1;
                } else if (o1.getStart() > o2.getStart()) {
                    return 1;
                }
                else {
                    return Integer.compare(o1.getEnd(), o2.getEnd());
                }
            }
        });

        for (String[] time : book_time) {
            int start = parseTime(time[0]);
            int end = parseTime(time[1]) + 10;
            priorityQueue.offer(new Reservation(start, end));
        }
        PriorityQueue<Reservation> reservations = new PriorityQueue<>(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                return Integer.compare(o1.getEnd(), o2.getEnd());
            }
        });
        while (!priorityQueue.isEmpty()){
            Reservation reservation = priorityQueue.poll();
            if(reservations.isEmpty()){
                reservations.offer(reservation);
                continue;
            }

            if(reservations.peek().getEnd() > reservation.getStart()){
                reservations.offer(reservation);
            } else {
                reservations.poll();
                reservations.offer(reservation);
            }
        }

        return reservations.size();
    }
}

class Reservation {
    private final int start;
    private final int end;

    public Reservation(int start, int end) {
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
    public String toString() {
        return "Reservation{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
