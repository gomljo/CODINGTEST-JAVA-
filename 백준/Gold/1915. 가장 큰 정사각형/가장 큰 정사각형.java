import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());

        int[][] array = new int[rowSize + 1][colSize + 1];

        for (int i = 1; i <= rowSize; i++) {
            String[] row = br.readLine().split("");
            for (int j = 1; j <= colSize; j++) {
                array[i][j] = Integer.parseInt(row[j-1]);
            }

        }
        int answer = 0;
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                if(array[i][j]==0){
                    continue;
                }
                array[i][j] =  Math.min(array[i-1][j-1], Math.min(array[i][j - 1], array[i - 1][j]))+1;
                answer = Math.max(answer, array[i][j]);
            }
        }
        System.out.println((int) Math.pow(answer, 2));
        br.close();
    }
}