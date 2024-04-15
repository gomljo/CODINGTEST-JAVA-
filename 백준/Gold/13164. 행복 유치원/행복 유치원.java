import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_VALUE = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] height = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int[] diff = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            diff[i] = height[i+1] - height[i];
        }
        Arrays.sort(diff);
        int answer = 0;
        for (int i = 0; i < n-k; i++) {
            answer += diff[i];
        }
        System.out.println(answer);
        bufferedReader.close();
    }
}