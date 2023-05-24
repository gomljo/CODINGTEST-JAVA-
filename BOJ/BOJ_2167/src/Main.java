import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tableInfo = br.readLine().split(" ");
        int row = Integer.parseInt(tableInfo[0]);
        int col = Integer.parseInt(tableInfo[1]);
        int[][] table = new int[row][col];
        for (int i = 0; i < row; i++) {
            String[] rowData = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                table[i][j] = Integer.parseInt(rowData[j]);
            }
        }
        int numOfQuery = Integer.parseInt(br.readLine());
        int[] answer = new int[numOfQuery];
        for (int i = 0; i < numOfQuery; i++) {
            int sum = 0;
            String[] query = br.readLine().split(" ");
            int rowStart = Integer.parseInt(query[0])-1;
            int colStart = Integer.parseInt(query[1])-1;
            int rowEnd = Integer.parseInt(query[2])-1;
            int colEnd = Integer.parseInt(query[3])-1;

            for (int j = rowStart; j <= rowEnd; j++) {
                for (int k = colStart; k <= colEnd; k++) {
                    sum += table[j][k];
                }
            }
            answer[i] = sum;
        }
        Arrays.stream(answer).forEach(System.out::println);
    }
}
