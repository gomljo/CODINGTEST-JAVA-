import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfTestCases = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfTestCases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] numbers = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                numbers[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers);
            long minDiff = Long.MAX_VALUE;
            long count = 0;
            int start = 0;
            int end = numbers.length - 1;

            while (start < end) {
                int sum = numbers[start] + numbers[end];
                long currentDiff = Math.abs(k - sum);

                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                    count = 1;
                }
                else if (currentDiff == minDiff) {
                    count++;
                }

                if (sum < k) {
                    start++;
                } else if(sum > k){
                    end--;
                }
                else {
                    start++;
                    end--;
                }
            }
            System.out.println(count);
        }


    }
}