import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int i = 0; i < n; i++) {
            pq.addAll(Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }
        int cnt = 0;
        while (!pq.isEmpty() && cnt < n-1){
            pq.poll();
            cnt++;
        }
        System.out.println(pq.poll());
    }
}