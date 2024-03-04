import java.util.Scanner;

public class Main {
    private static final int FIRST_QUAD = 0;
    private static final int SECOND_QUAD = 1;
    private static final int THIRD_QUAD = 2;
    private static final int FOURTH_QUAD = 3;

    // 재귀
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int order = decideOrder(0, row, column, n);
        System.out.println(order);
    }

    public static int decideOrder(int order, int row, int column, int n) {;
        if (n == 1) {
            int rowPosition = row % 2;
            int columnPosition = column % 2;
            return order + 2 * rowPosition + columnPosition;
        }
        int size = (int) Math.pow(2, n);
        int halfSize = size / 2;
        int rowQuad = row / halfSize;
        int columnQuad = column / halfSize;
        int quad = 2 * rowQuad + columnQuad;
        switch (quad) {
            case FIRST_QUAD:
                order = decideOrder(order, row % size, column % size, n - 1);
                break;
            case SECOND_QUAD:
                order = decideOrder(order + halfSize*halfSize, row % halfSize, column % halfSize , n - 1);
                break;
            case THIRD_QUAD:
                order = decideOrder(order + halfSize*halfSize * 2, row% halfSize, column% halfSize, n - 1);
                break;
            case FOURTH_QUAD:
                order = decideOrder(order + halfSize*halfSize * 3, row % halfSize, column % halfSize, n - 1);
                break;
        }
        return order;
    }

}