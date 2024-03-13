import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] lanCables = new int[k];

        for (int i = 0; i < k; i++) {
            lanCables[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lanCables);
        long maxCableLength = lanCables[k - 1];
        long minCableLength = 1;
        while (minCableLength <= maxCableLength) {
            long middleLength = (minCableLength + maxCableLength) / 2;

            int count = 0;
            for (int i = 0; i < k; i++) {
                int numberOfSplitLanCable = (int) (lanCables[i] / middleLength);
                count += numberOfSplitLanCable;
            }
            if (count < n) {

                maxCableLength = middleLength-1;
            } else {
                minCableLength = middleLength+1;
            }
        }
        System.out.println(maxCableLength);

        br.close();
    }
}