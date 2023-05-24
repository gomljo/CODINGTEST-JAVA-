public class Solution {
    static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static String[][] map;
    static int minDistanceLever = Integer.MAX_VALUE;
    static int minDistanceExit = Integer.MAX_VALUE;

    public boolean isDeadEnd(int[] specialPoint){
        int numOfWall = 0;
        for(int[] direction: directions){
            int x = specialPoint[0] + direction[0];
            int y = specialPoint[1] + direction[1];
            if(x < map.length && x >= 0 && y >= 0 && y < map[0].length){
                if(map[x][y].equals("X")){
                    numOfWall+=1;
                }
            }
            else {
                numOfWall+=1;
            }
        }
        return numOfWall==4;
    }
    public void findLever(int x, int y, int distance, boolean[][] visited){

        if(map[x][y].equals("L")){
            minDistanceLever = Math.min(distance, minDistanceLever);
            return;
        }

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if(newX < map.length && newX >= 0 && newY >= 0 && newY < map[0].length && !map[newX][newY].equals("X") && !visited[newX][newY]){
                visited[newX][newY] = true;
                findLever(newX, newY, distance + 1, visited);
                visited[newX][newY] = false;
            }
        }
    }

    public void findExit(int x, int y, int distance, boolean[][] visited){

        if(map[x][y].equals("E")){
            minDistanceExit = Math.min(distance, minDistanceExit);
            return;
        }

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if(newX < map.length && newX >= 0 && newY >= 0 && newY < map[0].length && !map[newX][newY].equals("X") && !visited[newX][newY]){
                visited[newX][newY] = true;
                findExit(newX, newY, distance + 1, visited);
                visited[newX][newY] = false;
            }
        }
    }

    public int solution(String[] maps) {
        int answer = -1;
        map = new String[maps.length][maps[0].length()];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];
        for(int i=0; i<maps.length; i++){
            String[] row = maps[i].split("");
            for (int j = 0; j < row.length; j++) {
                if(row[j].equals("S")){
                    start[1] = j;
                    start[0] = i;
                }
                if(row[j].equals("L")){
                    lever[1] = j;
                    lever[0] = i;
                }
                if(row[j].equals("E")){
                    exit[0] = i;
                    exit[1] = j;
                }
                map[i][j] = row[j];
            }
        }
        // 레버까지의 최소거리 구하고
        // 레버로부터 도착점까지의 최소 거리 구해야할듯?
        if(isDeadEnd(start) || isDeadEnd(lever) || isDeadEnd(exit)){
            return answer;
        }
        boolean[][] visitedLever = new boolean[maps.length][maps[0].length()];
        visitedLever[start[0]][start[0]] = true;
        findLever(start[0], start[1], 0, visitedLever);
        if(minDistanceLever==Integer.MAX_VALUE){
            return answer;
        }
        boolean[][] visitedExit = new boolean[maps.length][maps[0].length()];
        visitedExit[lever[0]][lever[1]] = true;

        findExit(lever[0], lever[1], 0, visitedExit);
        if(minDistanceExit==Integer.MAX_VALUE){
            return answer;
        }
        return minDistanceLever+minDistanceExit;
    }

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
//        String[] maps = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        Solution solution = new Solution();
        solution.solution(maps);
    }
}