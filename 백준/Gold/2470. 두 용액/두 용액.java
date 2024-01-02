import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] liquid = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        int[] res = new int[2];
        Arrays.sort(liquid);
        int start = 0;
        int end = liquid.length - 1;
        int min = Integer.MAX_VALUE;
        while (start < end) {
            int sum = liquid[start] + liquid[end];
            if(Math.abs(sum) < min){
                res[0] = liquid[start];
                res[1] = liquid[end];
                min = Math.abs(sum);
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
        System.out.printf("%d %d\n", res[0], res[1]);

    }
}