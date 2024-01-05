import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < numberOfCase; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] tree = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                tree[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(tree);
            int[] sorted = new int[n];
            sorted[n / 2] = tree[n - 1];
            int left = n / 2 - 1;
            int right = n / 2 + 1;
            int index = n - 2;
            int maxDiff = Integer.MIN_VALUE;
            while (index >= 0) {
                if(right < n){
                    sorted[right] = tree[index--];
                    maxDiff = Math.max(maxDiff, Math.abs(sorted[right-1] - sorted[right]));
                    right+=1;
                }
                if(left >= 0){
                    sorted[left] = tree[index--];
                    maxDiff = Math.max(maxDiff, Math.abs(sorted[left+1] - sorted[left]));
                    left-=1;
                }
            }
            maxDiff = Math.max(maxDiff, Math.abs(sorted[0] - sorted[n-1]));
            System.out.println(maxDiff);

        }
    }
}