import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end;
        int maxSequenceLength = 0;
        while (start < n){
            end = start;
            HashMap<Integer, Integer> map = new HashMap<>();
            int cnt = 0;
            while (end < n){
                map.put(sequence[end], map.getOrDefault(sequence[end], 0)+1);
                if(map.get(sequence[end]) > k){
                    break;
                }
                cnt++;
                end++;
            }
            if(cnt > maxSequenceLength){
                maxSequenceLength = cnt;
            }
            start++;

        }
        System.out.println(maxSequenceLength);
    }
}