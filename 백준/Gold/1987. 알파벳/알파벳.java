import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static char[][] board;
    private static int maxLength = 0;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }
        boolean[] alreadyMeetAlphabets = new boolean[26];
        alreadyMeetAlphabets[board[0][0] - 'A'] = true;
        boolean[][] visited = new boolean[r][c];
        visited[0][0] = true;
        findMaxPath(visited, alreadyMeetAlphabets, 0, 0, 1);
        System.out.println(maxLength);
        br.close();
    }

    public static void findMaxPath(boolean[][] visited, boolean[] alreadyMeetAlphabets, int row, int col, int length) {
        maxLength = Math.max(maxLength, length);
        for (int[] move : MOVES) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];
            if (isInBoard(nextRow, nextCol)
                    && !visited[nextRow][nextCol]) {

                if(alreadyMeetAlphabets[board[nextRow][nextCol]-'A']){
                    // 중간에 끝났을 때

                    continue;
                }

                visited[nextRow][nextCol] = true;
                alreadyMeetAlphabets[board[nextRow][nextCol] - 'A'] = true;
                findMaxPath(visited, alreadyMeetAlphabets, nextRow, nextCol, length + 1);
                alreadyMeetAlphabets[board[nextRow][nextCol] - 'A'] = false;
                visited[nextRow][nextCol] = false;
            }
        }

    }

    public static boolean isInBoard(int nextRow, int nextCol) {
        return (0 <= nextRow && nextRow < board.length) && (0 <= nextCol && nextCol < board[0].length);
    }
}