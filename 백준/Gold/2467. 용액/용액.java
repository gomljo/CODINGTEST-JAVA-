import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int ZERO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] solutions = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);
        int start = 0;
        int end = n - 1;
        int minDiff = Integer.MAX_VALUE;
        int solution1 = 0;
        int solution2 = 0;
        while (start < end) {
            int diff = Math.abs(solutions[start] + solutions[end]);

            if (diff <= minDiff) {
                minDiff = diff;
                solution1 = solutions[start];
                solution2 = solutions[end];
            }

            if(Math.abs(solutions[start+1] + solutions[end]) < diff){
                start++;
            } else if (Math.abs(solutions[start] + solutions[end-1]) < diff) {
                end--;
            } else {
                start++;
                end--;
            }
        }
        System.out.println(solution1 +" "+solution2);
        br.close();

    }
}