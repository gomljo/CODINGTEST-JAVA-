import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .mapToInt(sensor -> sensor)
                .toArray();
        Arrays.sort(sensors);
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < sensors.length - 1; i++) {
            diff.add(sensors[i + 1] - sensors[i]);
        }
        diff.sort(Comparator.reverseOrder());
        K -= 1;
        while (K > 0 && diff.size() > 0) {
            diff.remove(0);
            K--;
        }
        int answer = 0;
        for (int d : diff) {
            answer += d;
        }
        System.out.println(answer);


    }
}