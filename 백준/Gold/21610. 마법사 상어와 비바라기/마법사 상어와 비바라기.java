import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int rowSize;
    private static int[][] trainingField;
    private static int[][] magics;

    public static void input(BufferedReader bufferedReader) {
        try {
            String[] mapParams = bufferedReader.readLine().split(" ");
            rowSize = Integer.parseInt(mapParams[0]);
            int numberOfMagics = Integer.parseInt(mapParams[1]);
            trainingField = new int[rowSize][rowSize];
            for (int i = 0; i < rowSize; i++) {
                trainingField[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                        .map(Integer::valueOf)
                        .mapToInt(water -> water)
                        .toArray();
            }
            magics = new int[numberOfMagics][2];
            for (int i = 0; i < numberOfMagics; i++) {
                magics[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                        .map(Integer::valueOf)
                        .mapToInt(magic -> magic)
                        .toArray();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        Magician magician = new Magician(trainingField, rowSize, rowSize);
        Cloud cloud = new Cloud(rowSize, rowSize);
        DirectionTransformer transformer = new DirectionTransformer(magics);

        while (transformer.isOnProgress()) {

            MagicOrder magicOrder = transformer.getNextMove();
            cloud.getNextCloudPosition(magicOrder);
            magician.doRain(cloud);
            magician.doWaterDuplicateBug(cloud);
            List<int[]> updatedPositions = magician.makeCloud(cloud);
            cloud.clearCloud();
            cloud.addCloud(updatedPositions);
            cloud.plusMoveCount();
        }
        System.out.println(magician.getTotalWater());
    }
}

class Magician {
    // 0: 좌상, 1: 우상, 2: 좌하, 3: 우하
    private final int[][] cross = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private final int[][] field;
    private final int rowSize;
    private final int columnSize;
    private static final int CLOUD_MAKING_COST = 2;
    private static final int CLOUD_MAKING_LIMIT = 2;


    public Magician(int[][] providedField, int rowSize, int columnSize) {
        this.field = providedField;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public List<int[]> makeCloud(Cloud cloud) {
        List<int[]> updatedPositions = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                int[] currentPosition = new int[]{row, column};
                if (this.field[row][column] >= CLOUD_MAKING_LIMIT && !cloud.isInCloudField(currentPosition)) {
                    this.field[row][column] -= CLOUD_MAKING_COST;
                    updatedPositions.add(currentPosition);
                }
            }
        }
        return updatedPositions;
    }

    public void doRain(Cloud cloud) {
        for (int[] position : cloud.getCurrentPositions()) {
            this.field[position[0]][position[1]] += 1;
        }
    }

    public void doWaterDuplicateBug(Cloud cloud) {
        for (int[] position : cloud.getCurrentPositions()) {
            int crossWaterExistenceCount = 0;
            for (int[] crossMove : this.cross) {
                int movedRow = position[0] + crossMove[0];
                int movedColumn = position[1] + crossMove[1];
                if (isCrossBlockInField(movedRow, movedColumn) && existWaterAt(movedRow, movedColumn)) {
                    crossWaterExistenceCount++;
                }
            }
            this.field[position[0]][position[1]] += crossWaterExistenceCount;
        }
    }

    private boolean isCrossBlockInField(int movedRow, int movedColumn) {
        return (0 <= movedRow && movedRow < this.rowSize) && (0 <= movedColumn && movedColumn < this.columnSize);
    }

    private boolean existWaterAt(int movedRow, int movedColumn) {
        return this.field[movedRow][movedColumn] > 0;
    }

    public int getTotalWater(){
        int totalWater = 0;
        for (int row = 0; row < this.rowSize; row++) {
            for (int column = 0; column < this.columnSize; column++) {
                totalWater += this.field[row][column];
            }
        }
        return totalWater;
    }

    public void printField(){
        System.out.println("current field state");
        for (int[] row : this.field) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

class Cloud {
    private final List<int[]> positions = new ArrayList<>();
    private final int rowSize;
    private final int columnSize;
    private int numberOfMove;

    public Cloud(int rowSize, int columnSize) {
        this.positions.add(new int[]{rowSize - 2, 0});
        this.positions.add(new int[]{rowSize - 2, 1});
        this.positions.add(new int[]{rowSize - 1, 0});
        this.positions.add(new int[]{rowSize - 1, 1});
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.numberOfMove = 0;
    }

    public void getNextCloudPosition(MagicOrder magicOrder) {
        for (int[] position : positions) {
            for (int j = 0; j < magicOrder.getDistance(); j++) {
                position[0] = calculateCoordinate(position[0], magicOrder.getDeltaRow(), this.rowSize);
                position[1] = calculateCoordinate(position[1], magicOrder.getDeltaColumn(), this.columnSize);
            }
        }
    }

    public void clearCloud(){
        this.positions.clear();
    }

    public void addCloud(List<int[]> updatedPositions){
        this.positions.addAll(updatedPositions);
    }
    private int calculateCoordinate(int current, int move, int limit) {
        if (current + move == limit) {
            return 0;
        }

        if (current + move < 0) {
            return limit - 1;
        }
        return current + move;
    }

    public boolean isInCloudField(int[] currentPosition) {
        for (int[] position : this.positions) {
            if (currentPosition[0] == position[0] && currentPosition[1] == position[1]) {
                return true;
            }
        }
        return false;
    }

    public List<int[]> getCurrentPositions() {
        return this.positions;
    }

    public void plusMoveCount(){
        this.numberOfMove++;
    }


    public void printCurrentPosition(){
        System.out.println("current stage: "+ this.numberOfMove);
        System.out.println("current position");
        for (int[] position : this.positions) {
            System.out.println(Arrays.toString(position));
        }
        System.out.println();
    }
}

class DirectionTransformer {
    // 0: 좌, 1:좌상, 2:상, 3:우상, 4:우, 5:우하, 6:하, 7:좌하
    private final int[][] directions = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    private final int[] directionSequence;
    private final int[] distanceSequence;
    private int currentOrderIndex;

    public DirectionTransformer(int[][] order) {
        directionSequence = new int[order.length];
        distanceSequence = new int[order.length];
        initialize(order);
        this.currentOrderIndex = 0;
    }

    private void initialize(int[][] order) {
        for (int i = 0; i < order.length; i++) {
            directionSequence[i] = order[i][0]-1;
            distanceSequence[i] = order[i][1];

        }
    }

    public MagicOrder getNextMove() {
        int nextDirection = this.directionSequence[this.currentOrderIndex];
        int nextDistance = this.distanceSequence[this.currentOrderIndex];
        currentOrderIndex++;
        return new MagicOrder(directions[nextDirection][0], directions[nextDirection][1], nextDistance);
    }

    public boolean isOnProgress() {
        return this.currentOrderIndex < this.directionSequence.length;
    }

}

class MagicOrder {
    private final int deltaRow;
    private final int deltaColumn;
    private final int distance;

    public MagicOrder(int deltaRow, int deltaColumn, int distance) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
        this.distance = distance;
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaColumn() {
        return deltaColumn;
    }

    public int getDistance() {
        return distance;
    }
}