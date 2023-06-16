import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] sushiTable = new int[N+k-1];

        for (int i = 0; i < N; i++) {
            int sushiNumber = Integer.parseInt(br.readLine());
            sushiTable[i] = sushiNumber;
        }
        for (int i=0; i<k-1; i++){
            sushiTable[N+i] = sushiTable[i];
        }

        int maxDiversity = 0;
        for(int i=0; i<sushiTable.length-k+1; i++){
            Set<Integer> sequence = new HashSet<>();
            sequence.add(c);
            for (int j = i; j < i+k; j++) {
                sequence.add(sushiTable[j]);
            }
            maxDiversity = Math.max(maxDiversity, sequence.size());
        }
        System.out.println(maxDiversity);
    }
}