import java.util.Arrays;

public class Solution {
    private static final int[] moveRow = new int[]{0, 0, -1, 1};
    private static final int[] moveCol = new int[]{1, -1, 0, 0};
    private static int maxSize = 1;

    public static void search(Coordinate coordinate, int[][] map) {
        int color = map[coordinate.getRow()][coordinate.getCol()];
        map[coordinate.getRow()][coordinate.getCol()] = 0;

        for (int i = 0; i < moveCol.length; i++) {
            int row = coordinate.getRow() + moveRow[i];
            int col = coordinate.getCol() + moveCol[i];
            if ((0 <= row && row < map.length) && (0 <= col && col < map[0].length) && map[row][col] != 0) {
                if(map[row][col]==color){
                    maxSize++;
                    search(new Coordinate(row, col),  map);

                }
            }
        }
    }

    public static int[] find(int[][] map){
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]!=0) {
                    search(new Coordinate(i, j),  map);
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSize, maxSizeOfOneArea);
                    maxSize = 1;
                }
            }
        }
        return new int[] {numberOfArea, maxSizeOfOneArea};
    }


    public int[] solution(int m, int n, int[][] picture) {

        int[] answer;


        // 맵을 돌면서 0이 아닌 것부터 시작하고
        // 만약 0이 아닌 것부터 시작했다면 같은 숫자가 아닌 경우 재귀 종료
        answer = find(picture);
        return answer;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] pic = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        Solution solution = new Solution();
        solution.solution(m, n, pic);
    }
}

class Coordinate {
    private int row;
    private int col;


    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}