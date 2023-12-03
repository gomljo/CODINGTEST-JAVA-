import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int M = Integer.parseInt(params[1]);

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(numbers);
        int start = 0;
        int end = start + 1;
        int answer = Integer.MAX_VALUE;
        while (end < N && start < end) {

            if (numbers[end] - numbers[start] < M) {
                end++;
            } else {
                answer = Math.min(answer, numbers[end] - numbers[start]);
                start++;
                end = start+1;
            }
        }
        System.out.println(answer);
    }
}