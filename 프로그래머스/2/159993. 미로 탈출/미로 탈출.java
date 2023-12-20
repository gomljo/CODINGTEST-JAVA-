import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static int rowSize;
    private static int colSize;
    private static String[][] twoDimensionMap;
    private static final int[] dROW = new int[]{0, 0, -1, 1};
    private static final int[] dCOL = new int[]{1, -1, 0, 0};
    public boolean isDeadEnd(int[] specialPoint){
        int numOfWall = 0;
        for(int i=0; i<dROW.length; i++){
            int x = specialPoint[0] + dROW[i];
            int y = specialPoint[1] + dCOL[i];
            if(x < rowSize && x >= 0 && y >= 0 && y < colSize){
                if(twoDimensionMap[x][y].equals("X")){
                    numOfWall+=1;
                }
            }
            else {
                numOfWall+=1;
            }
        }
        return numOfWall==4;
    }
    public static boolean isIn(int nextRow, int nextCol) {
        return (0 <= nextRow && nextRow < rowSize) && (0 <= nextCol && nextCol < colSize);
    }

    public static boolean isWall(int nextRow, int nextCol) {
        return twoDimensionMap[nextRow][nextCol].equals("X");
    }

    public static void findTarget(int currentRow, int currentCol, String target, int time, boolean[][] visited, int[] minimumTime) {
        if (twoDimensionMap[currentRow][currentCol].equals(target)) {
            if (target.equals("L")) {
                minimumTime[0] = Math.min(minimumTime[0], time);
            }
            else {
                minimumTime[1] = Math.min(minimumTime[1], time);
            }
            return;
        }
        for (int i = 0; i < dROW.length; i++) {
            int nextRow = currentRow + dROW[i];
            int nextCol = currentCol + dCOL[i];

            if (!isIn(nextRow, nextCol)) {
                continue;
            }

            if (isWall(nextRow, nextCol)) {
                continue;
            }

            if (!visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                findTarget(nextRow, nextCol, target, time + 1, visited, minimumTime);
            }

        }
    }

    public int solution(String[] maps) {
        int answer = 0;
        rowSize = maps.length;
        colSize = maps[0].length();
        // 이차원 미로 초기화
        twoDimensionMap = new String[rowSize][colSize];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];
        for (int row = 0; row < rowSize; row++) {
            String[] rowData = maps[row].split("");
            for (int col = 0; col < colSize; col++) {
                twoDimensionMap[row][col] = rowData[col];
                switch (rowData[col]) {
                    case "S":
                        start[0] = row;
                        start[1] = col;
                        break;
                    case "L":
                        lever[0] = row;
                        lever[1] = col;
                        break;
                    case "E":
                        exit[0] = row;
                        exit[1] = col;
                        break;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowSize][colSize];
        visited[start[0]][start[1]] = true;
        queue.add(new int[]{start[0], start[1], 0});
        boolean isPullLever = false;
        while (!queue.isEmpty()){

            int[] position = queue.poll();

            if(position[0]==lever[0] && position[1] == lever[1]){
                answer = position[2];
                isPullLever = true;
                break;
            }

            for (int i = 0; i < dROW.length; i++) {
                int nextRow = position[0] + dROW[i];
                int nextCol = position[1] + dCOL[i];

                if(isIn(nextRow, nextCol) && !isWall(nextRow, nextCol) && !visited[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol, position[2]+1});
                }
            }
        }

        if (!isPullLever){
            return -1;
        }

        queue = new LinkedList<>();
        visited = new boolean[rowSize][colSize];
        visited[lever[0]][lever[1]] = true;
        boolean isExit = false;
        queue.add(new int[]{lever[0], lever[1], 0});
        while (!queue.isEmpty()){
            int[] position = queue.poll();

            if(position[0]==exit[0] && position[1] == exit[1]){
                answer += position[2];
                isExit = true;
                break;
            }

            for (int i = 0; i < dROW.length; i++) {
                int nextRow = position[0] + dROW[i];
                int nextCol = position[1] + dCOL[i];
                if(isIn(nextRow, nextCol) && !isWall(nextRow, nextCol) && !visited[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol, position[2]+1});
                }
            }
        }
        if(!isExit){
            return -1;
        }
        return answer;
    }

}