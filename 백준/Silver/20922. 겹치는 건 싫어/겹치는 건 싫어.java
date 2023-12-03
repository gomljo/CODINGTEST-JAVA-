import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int K = Integer.parseInt(params[1]);

        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .mapToInt(number -> number).toArray();

        int start = 0;
        int end = 0;
        int[] cnt = new int[100_001];
        int answer = 0;
        while (end<N){

            while (end < N && cnt[numbers[end]]+1 <=K){
                cnt[numbers[end]]++;
                end++;
            }

            int length = end - start;
            answer = Math.max(answer, length);
            cnt[numbers[start]]--;
            start++;
        }
        System.out.println(answer);
    }
}