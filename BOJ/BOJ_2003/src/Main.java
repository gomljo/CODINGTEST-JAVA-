import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        int start = 0;
        int end = start;
        while (end < arr.length){
            int sum=0;
            for (int i = start; i <= end; i++) {
                sum += arr[i];
            }

            if(sum < M){
                end += 1;
            }
            else if(sum==M){
                answer+=1;
                start += 1;
                end = start;

            }
            else{
                start+=1;
            }
        }
        System.out.println(answer);
    }
}
