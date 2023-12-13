
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

class Solution {
    private static int[][] array;
    private static final int[][] DELTA_QUADRANT = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    private static final List<Integer> elements = new ArrayList<>();

    public static int checkAllElementSame(int size, int startRow, int endRow, int startCol, int endCol) {
        boolean isSame = true;
        int compare = array[startRow][startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (array[i][j] != compare) {
                    isSame = false;
                    break;
                }
            }
        }

        if (!isSame) {
            return -1;
        }
        return compare;
    }

    public static void quadCompression(int size, int startRow, int endRow, int startCol, int endCol) {
        int result = checkAllElementSame(size, startRow, endRow, startCol, endCol);
        if (result == -1) {
            int half = size / 2;
            int midRow = (startRow + endRow) / 2;
            int midCol = (startCol + endCol) / 2;
            quadCompression(half, startRow, midRow, startCol, midCol);
            quadCompression(half, startRow, midRow, midCol, endCol);
            quadCompression(half, midRow, endRow, startCol, midCol);
            quadCompression(half, midRow, endRow, midCol, endCol);
        } else {
            elements.add(result);
        }


    }


    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        int size = arr.length;

        array = new int[size][size];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i].clone();
        }
        quadCompression(size, 0, size, 0, size);
        answer[0] = (int) elements.stream().filter(element -> element == 0).count();
        answer[1] = (int) elements.stream().filter(element -> element == 1).count();

        return answer;
    }

}
