import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int max_sweet_potato = 0;
    private static int time;
    private static int rowSize;
    private static int colSize;
    private static String[][] map;
    private static int[] dRow = new int[]{0, 0, -1, 1, 0};
    private static int[] dCol = new int[]{1, -1, 0, 0, 0};


    public static void findSweetPotato(int currentTime, int currentRow, int currentCol, int currentSweetPotato) {
        max_sweet_potato = Math.max(max_sweet_potato, currentSweetPotato);
        if (currentTime == time) {
            return;
        }
        String prev;
        for (int i = 0; i < dRow.length; i++) {
            int nextRow = currentRow + dRow[i];
            int nextCol = currentCol + dCol[i];

            if (!isIn(nextRow, nextCol)) {
                continue;
            }
            if (map[nextRow][nextCol].equals("#")) {
                continue;
            }
            int count = map[nextRow][nextCol].equals("S") ? 1 : 0;
            prev = map[nextRow][nextCol];
            map[nextRow][nextCol] = ".";
            findSweetPotato(currentTime + 1, nextRow, nextCol, currentSweetPotato+count);
            map[nextRow][nextCol] = prev;
        }

    }

    public static boolean isIn(int nextRow, int nextCol) {
        return (0 <= nextRow && nextRow < rowSize) && (0 <= nextCol && nextCol < colSize);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        rowSize = Integer.parseInt(params[0]);
        colSize = Integer.parseInt(params[1]);
        time = Integer.parseInt(params[2]);
        map = new String[rowSize][colSize];
        boolean[][] visited = new boolean[rowSize][colSize];
        int[] start = new int[2];
        for (int i = 0; i < rowSize; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < row.length; j++) {
                map[i][j] = row[j];
                if (row[j].equals("G")) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        visited[start[0]][start[1]] = true;
        findSweetPotato(0, start[0], start[1], 0);
        System.out.println(max_sweet_potato);

    }
}