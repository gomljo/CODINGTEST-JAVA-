import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int DIAGONAL = 2;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static int size;
    public static int[][] map;
    public static int ans;


    public static boolean isPossibleToMoveVertical(int row, int col) {
        return (row <= size && col + 1 <= size) && (map[row][col+1]==0);
    }

    public static boolean isPossibleToMoveDiagonal(int row, int col) {
        return (row+1 <= size && col+1 <= size) && (map[row+1][col+1]==0) &&(map[row][col+1]==0)&&(map[row+1][col]==0);
    }

    public static boolean isPossibleToMoveHorizontal(int row, int col) {
        return (row+1 <= size && col <= size) && (map[row+1][col]==0);
    }

    public static void findPossible(int row, int col, int direction) {

        if (row == size && col == size) {
            ans++;
            return;
        }
        switch (direction){
            case 0:
                if (isPossibleToMoveVertical(row, col)) {
                    findPossible(row, col+1, VERTICAL);
                }
                break;
            case 1:
                if(isPossibleToMoveHorizontal(row, col)){
                    findPossible(row+1, col, HORIZONTAL);
                }
                break;
            case 2:
                if (isPossibleToMoveVertical(row, col)) {
                    findPossible(row, col+1, VERTICAL);
                }

                if(isPossibleToMoveHorizontal(row, col)){
                    findPossible(row+1, col, HORIZONTAL);
                }
                break;
        }
        if (isPossibleToMoveDiagonal(row, col)) {
            findPossible(row+1, col+1, DIAGONAL);
        }



    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        map = new int[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans=0;
        findPossible(1,2, 0);
        System.out.println(ans);

    }
}