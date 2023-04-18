import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {

    static int maxPoints = 0;
    static int n = 0;
    static int k = 0;
    static int[] bullets;
    
    static void makeNewTarget(int[][] board, int row, int col, int health){
        if(row-1>=0 && board[row-1][col]==0){
            board[row-1][col] = health;
        }
        if(row+1 < board.length &&board[row+1][col]==0){
            board[row+1][col] = health;
        }
        if(col-1 >=0 && board[row][col-1]==0){
            board[row][col-1] = health;
        }
        if(col+1 < board[0].length && board[row][col+1]==0){
            board[row][col+1] = health;
        }

    }

    static void backtracking(int[][] boardHealth, int[][] boardScore, int depth, int pts){

        if(depth==k){
            if(pts > maxPoints){
                maxPoints = pts;
            }
            return;
        }

        for(int i=0; i<n; i++){
            int bullet = bullets[depth];
            int[][] newBoardHealth = new int[boardHealth.length][boardHealth[0].length];
            int[][] newBoardScore = new int[boardHealth.length][boardHealth[0].length];

            int newPts=0;

            for(int k=0; k<boardHealth.length; k++){
                newBoardHealth[k] = boardHealth[k].clone();
            }

            for(int h=0; h<boardHealth.length; h++){
                newBoardScore[h] = boardScore[h].clone();
            }

            for(int j=0; j< boardHealth[i].length; j++){

                if(boardHealth[i][j] > 0){
                    if(10 <= boardHealth[i][j]){
                        newPts = pts+boardScore[i][j];
                        newBoardHealth[i][j] = 0;
                        newBoardScore[i][j] = 0;
                    }
                    else if(boardHealth[i][j] - bullet <=0){
                        newBoardHealth[i][j] = 0;
                        newBoardScore[i][j] = 0;
                        newPts = pts + boardScore[i][j];
                        int newHealth = boardScore[i][j] / 4;
                        if(newHealth != 0){
                            makeNewTarget(newBoardHealth, i, j, newHealth);
                            makeNewTarget(newBoardScore, i, j, newHealth);
                        }
                    }else {
                       newBoardHealth[i][j] -= bullet;
                    }
                    break;
                }
            }
            backtracking(newBoardHealth,newBoardScore, depth+1, newPts);
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        int[][] boardScore = new int[n][n];
        int[][] boardHealth = new int[n][n];
        bullets = new int[k];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int element = Integer.parseInt(st.nextToken());
                boardScore[i][j] = element;
                boardHealth[i][j] = element;
            }
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<k; i++){
            bullets[i] = Integer.parseInt(st2.nextToken());
        }
        backtracking(boardHealth, boardScore,0, 0);
        System.out.println(maxPoints);
    }
}
