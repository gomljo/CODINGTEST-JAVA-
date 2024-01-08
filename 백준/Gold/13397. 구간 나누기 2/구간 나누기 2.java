import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int isDividedToMBLock(int[] numbers, int maxLimit) {
        int numberOfBLocks = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            min = Math.min(numbers[i], min);
            max = Math.max(numbers[i], max);
            if (max - min > maxLimit) {
                numberOfBLocks++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }
        return numberOfBLocks;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n];
        int start = 0;
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            end = Math.max(numbers[i], end);
        }

        while (start < end) {
            int mid = (start + end) / 2;

            if (isDividedToMBLock(numbers, mid) <= m) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(end);

    }
}