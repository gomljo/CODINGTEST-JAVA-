import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        int answer = Integer.MAX_VALUE;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String params = bufferedReader.readLine();
        StringTokenizer st = new StringTokenizer(params, " ");

        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());

        String numberSequence = bufferedReader.readLine();
        st = new StringTokenizer(numberSequence);
        long[] sequence = new long[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = Long.parseLong(st.nextToken());
        }

        long[] cumSum = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            cumSum[i] = cumSum[i - 1] + sequence[i - 1];
        }
        int start = 0;
        int end = 1;

        while (end <= n && start <= end) {

            if (cumSum[end] - cumSum[start] < target) {
                end++;
            } else {
                answer = Math.min(answer, Math.abs(end - start));
                start++;
            }
        }
        if (answer==Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);
    }
}