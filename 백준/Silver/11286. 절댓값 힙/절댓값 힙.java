import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                int diff = Math.abs(o1) - Math.abs(o2);
                if (diff < 0) {
                    return -1;
                } else if (diff == 0) {
                    return Integer.compare(o1 - o2, 0);
                }
                else {
                    return 1;
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int number = sc.nextInt();
            if (number != 0) {
                pq.add(number);
            } else {
                if (pq.isEmpty()) {
                    sb.append(0);
                    sb.append(" ");
                } else {
                    sb.append(pq.poll());
                    sb.append(" ");
                }
            }
            n--;
        }
        String answer = sb.toString();
        System.out.println(String.join("\n", answer.split(" ")));
    }
}