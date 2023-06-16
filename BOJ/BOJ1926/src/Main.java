import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, cnt, maxArea;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        maxArea = 0;


        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int val = Integer.parseInt(st.nextToken());
                    board[i][j] = val;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j]==1 && !visited[i][j]) {
                    BFS(i, j);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(maxArea);
    }
    static void BFS(int i, int j){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i, j));
        int area = 0;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        while (!queue.isEmpty()){
            Pair p = queue.poll();
            area++;
            for (int d= 0; d < 4; d++){
                int ni = p.x + dx[d];
                int nj = p.y + dy[d];

                if(ni < 0 || ni >= n || nj < 0 || nj >=m) {
                    continue;
                }
                if(visited[ni][nj] || board[ni][nj]==0){
                    continue;
                }
                visited[ni][nj] = true;
                queue.add(new Pair(ni, nj));
            }
        }
        cnt++;
        if(area > 1) {
            area--;
        }
        maxArea = Math.max(maxArea, area);

    }

}
class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
