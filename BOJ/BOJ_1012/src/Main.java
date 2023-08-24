import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfFarm = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfFarm; i++) {

            // 배추밭 초기화 코드
            String[] params = br.readLine().split(" ");
            int colSize = Integer.parseInt(params[0]);
            int rowSize = Integer.parseInt(params[1]);
            int numberOfCabbage = Integer.parseInt(params[2]);
            int[][] farm = new int[rowSize][colSize];

            Queue<int[]> cabbageLocation = new LinkedList<>();

            for (int j = 0; j < numberOfCabbage; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                cabbageLocation.add(new int[]{row, col});
                farm[row][col] = 1;
            }

            // 필요한 흰 지렁이 갯수 구하기
            boolean[][] visited = new boolean[rowSize][colSize];
            int numberOfWhiteEarthWorm = 0;

            while (!cabbageLocation.isEmpty()) {

                Queue<int[]> subFarm = new LinkedList<>();

                int[] start = cabbageLocation.poll();
                if (!visited[start[0]][start[1]]) {
                    visited[start[0]][start[1]] = true;
                    subFarm.add(start);
                    numberOfWhiteEarthWorm += 1;
                    while (!subFarm.isEmpty()) {
                        int[] location = subFarm.poll();
                        int row = location[0];
                        int col = location[1];
                        for (int j = 0; j < dx.length; j++) {
                            int newRow = row + dx[j];
                            int newCol = col + dy[j];
                            if ((0 <= newRow && newRow < rowSize) && (0 <= newCol && newCol < colSize) && !visited[newRow][newCol] && farm[newRow][newCol] == 1) {
                                visited[newRow][newCol] = true;
                                subFarm.add(new int[]{newRow, newCol});
                            }
                        }

                    }
                }
            }
            System.out.println(numberOfWhiteEarthWorm);
        }


    }
}
