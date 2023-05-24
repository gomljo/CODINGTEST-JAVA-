import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int d[][] = new int[n+1][n+1];
        int a[][] = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]) + a[i][j];

            }
            for (int k = 0; k < a.length; k++) {
                System.out.printf("d[%d] = %s\n", k, Arrays.toString(d[k]));
            }
            System.out.println();
        }
        int ans=0;
        //마지막층 각각에 최댓값을 가지고 있어서 가장 큰 값 찾아준다.
        for(int i=1; i<=n; i++) {
            if(ans < d[n][i]) {
                ans = d[n][i];
            }
        }

        System.out.println(ans);

    }

}
