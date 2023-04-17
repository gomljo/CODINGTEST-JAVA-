import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());
        long H = Long.parseLong(br.readLine());
        long answer = 1;
        for (int i = 0; i < N-1; i++) {
            answer = (answer * M) % 1000000007;
        }
        System.out.println(String.valueOf(answer));
    }
}
