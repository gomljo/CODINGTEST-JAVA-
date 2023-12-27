import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int rowSize;
    private static int columnSize;
    private static int numberOfOperation;
    private static int[][] array;
    private static int[] operations;

    public static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] params = br.readLine().split(" ");
            rowSize = Integer.parseInt(params[0]);
            columnSize = Integer.parseInt(params[1]);
            numberOfOperation = Integer.parseInt(params[2]);
            array = new int[rowSize][columnSize];
            for (int i = 0; i < rowSize; i++) {
                array[i] = Arrays.stream(br.readLine().split(" "))
                        .map(Integer::valueOf)
                        .mapToInt(number -> number)
                        .toArray();
            }
            operations = Arrays.stream(br.readLine().split(" "))
                        .map(Integer::valueOf)
                        .mapToInt(operation -> operation)
                        .toArray();
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public static void simulate() {
        ArrayOperator arrayOperator = new ArrayOperator(array);
        for (int operation : operations) {
            operate(operation, arrayOperator);
        }
        arrayOperator.printArray();
    }

    public static void operate(int operation, ArrayOperator arrayOperator) {

        switch (operation) {
            case 1:
                arrayOperator.flipUpAndDown();
                break;
            case 2:
                arrayOperator.flipLeftAndRight();
                break;
            case 3:
                arrayOperator.rotateRightNinety();
                break;
            case 4:
                arrayOperator.rotateLeftNinety();
                break;
            case 5:
                arrayOperator.rotateQuadraticRight();
                break;
            case 6:
                arrayOperator.rotateQuadraticLeft();
                break;
            default:
                System.out.println("실행할 수 없는 연산입니다.");
        }
    }

    public static void main(String[] args) {
        input();
        simulate();

    }
}

class ArrayOperator {
    private int[][] array;
    private final int rowSize;
    private final int columnSize;

    public ArrayOperator(int[][] array) {
        this.array = array;
        this.rowSize = array.length;
        this.columnSize = array[0].length;
    }

    public void printArray() {
        for (int[] row : this.array) {
            for (int j = 0; j < this.array[0].length; j++) {
                System.out.printf("%d ", row[j]);
            }
            System.out.println();
        }
    }
    

    private void swap(int rowIndexA, int rowIndexB, int columnIndexA, int columnIndexB) {

        int temp = this.array[rowIndexA][columnIndexA];
        this.array[rowIndexA][columnIndexA] = this.array[rowIndexB][columnIndexB];
        this.array[rowIndexB][columnIndexB] = temp;
    }

    public void flipUpAndDown() {
        for (int j = 0; j < this.array[0].length; j++) {
            for (int i = 0; i < this.array.length / 2; i++) {
                this.swap(i, this.array.length - 1 - i, j, j);
            }
        }
    }

    public void flipLeftAndRight() {

        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[0].length / 2; j++) {
                this.swap(i, i, j, this.array[0].length - 1 - j);
            }
        }
    }

    private int[][] createNewArray() {
        return new int[this.array.length][this.array[0].length];
    }

    private int[][] createRotatedArray() {
        return new int[this.array[0].length][this.array.length];
    }

    public void rotateRightNinety() {
        int[][] rightRotatedArray = createRotatedArray();
        for (int i = 0; i < this.array.length; i++) {
            int[] row = this.array[i];
            for (int j = 0; j < row.length; j++) {
                rightRotatedArray[j][rightRotatedArray[0].length - 1 - i] = row[j];
            }
        }
        this.array = rightRotatedArray;
    }

    public void rotateLeftNinety() {
        int[][] leftRotatedArray = createRotatedArray();
        for (int i = 0; i < this.array.length; i++) {
            int[] row = this.array[i];
            for (int j = 0; j < row.length; j++) {
                leftRotatedArray[j][i] = row[row.length - 1 - j];
            }
        }
        this.array = leftRotatedArray;
    }

    public void rotateQuadraticLeft() {
        int[][] leftQuadraticArray = createNewArray();
        int rowHalf = this.array.length / 2;
        int columnHalf = this.array[0].length / 2;

        for (int i = 0; i < 4; i++) {
            int[] current = calculateCurrentIndex(i, rowHalf, columnHalf);
            int[] next = calculateLeftMovedIndex(i, rowHalf, columnHalf);
            for (int j = 0; j < rowHalf; j++) {
                for (int k = 0; k < columnHalf; k++) {
                    leftQuadraticArray[next[0] + j][next[1] + k] = this.array[current[0] + j][current[1] + k];
                }
            }
        }
        this.array = leftQuadraticArray;
    }

    private int[] calculateCurrentIndex(int nQuadratic, int halfRow, int halfColumn) {
        int[] indexes = new int[2];
        switch (nQuadratic) {
            case 0:
                indexes = new int[]{0, 0};
                break;
            case 1:
                indexes = new int[]{0, halfColumn};
                break;
            case 2:
                indexes = new int[]{halfRow, halfColumn};
                break;
            case 3:
                indexes = new int[]{halfRow, 0};
                break;
            default:
                break;
        }
        return indexes;
    }

    private int[] calculateLeftMovedIndex(int nQuadratic, int halfRow, int halfColumn) {
        int[] indexes = new int[2];
        switch (nQuadratic) {
            case 0:
                indexes = new int[]{halfRow, 0};

                break;
            case 1:
                indexes = new int[]{0, 0};
                break;
            case 2:
                indexes = new int[]{0, halfColumn};
                break;
            case 3:
                indexes = new int[]{halfRow, halfColumn};
                break;
            default:
                break;
        }
        return indexes;
    }

    public void rotateQuadraticRight() {
        int[][] rightQuadraticArray = createNewArray();
        int rowHalf = this.array.length / 2;
        int columnHalf = this.array[0].length / 2;

        for (int i = 0; i < 4; i++) {
            int[] current = calculateCurrentIndex(i, rowHalf, columnHalf);
            int[] next = calculateRightMovedIndex(i, rowHalf, columnHalf);
            for (int j = 0; j < rowHalf; j++) {
                for (int k = 0; k < columnHalf; k++) {
                    rightQuadraticArray[next[0] + j][next[1] + k] = this.array[current[0] + j][current[1] + k];
                }
            }
        }
        this.array = rightQuadraticArray;
    }

    private int[] calculateRightMovedIndex(int nQuadratic, int halfRow, int halfColumn) {
        int[] indexes = new int[2];
        switch (nQuadratic) {
            case 0:
                indexes = new int[]{0, halfColumn};
                break;
            case 1:
                indexes = new int[]{halfRow, halfColumn};
                break;
            case 2:
                indexes = new int[]{halfRow, 0};
                break;
            case 3:
                indexes = new int[]{0, 0};
                break;
            default:
                break;
        }
        return indexes;
    }
}