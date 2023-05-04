import java.io.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer;

        int num = Integer.parseInt(br.readLine());

        long[][] dp = new long[num+1][num+1];

        dp[1][1] = 1;

        for(int i=1; i<=num; i++){
            stringTokenizer = new StringTokenizer(br.readLine());

            for(int j=1; j<=num; j++){
                int moveLength = Integer.parseInt(stringTokenizer.nextToken());

                if(dp[i][j] >= 1 && moveLength != 0) {
                    if (j + moveLength <= num)
                        dp[i][j + moveLength] += dp[i][j];

                    if (i + moveLength <= num)
                        dp[i + moveLength][j] += dp[i][j];
                }
            }
        }

        bw.write(dp[num][num] + "\n");
        bw.flush();
    }

}
