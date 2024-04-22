import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] map;
        int[] min = new int[3];
        int[] max = new int[3];
        for (int i = 0; i < size; i++) {
            map = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            int[] minTemp = new int[3];
            int[] maxTemp = new int[3];
            minTemp[0] = Math.min(min[0], min[1]) + map[0];
            maxTemp[0] = Math.max(max[0], max[1]) + map[0];

            minTemp[1] = Math.min(Math.min(min[0], min[1]), min[2]) + map[1];
            maxTemp[1] = Math.max(Math.max(max[0], max[1]), max[2]) + map[1];

            minTemp[2] = Math.min(min[1], min[2]) + map[2];
            maxTemp[2] = Math.max(max[1], max[2]) + map[2];
            for (int j = 0; j < 3; j++) {
                min[j]=minTemp[j];
                max[j]=maxTemp[j];
            }
        }

        int minValue = Math.min(Math.min(min[0], min[1]), min[2]);
        int maxValue = Math.max(Math.max(max[0], max[1]), max[2]);
        System.out.printf("%d %d", maxValue, minValue);
    }
}