import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
    // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
    // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
    // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
    // 종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자. 가장 처음 수행되는 단계는 1번째 단계이다.
    // 내구도가 내려가는 일은 컨베이어 벨트에 적재할 때와 이동할 때 일어난다.
    private static int[] durability;
    private static int beltLength;
    private static int brokenBlockLimit;

    public static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] params = br.readLine().split(" ");
            beltLength = Integer.parseInt(params[0]);
            brokenBlockLimit = Integer.parseInt(params[1]);
            durability = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).mapToInt(d -> d).toArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static int simulate() {
        int stage = 0;
        ConveyerBelt conveyerBelt = new ConveyerBelt(durability, beltLength);
        // 컨베이어 벨트 블록 상태 점검하여
        while (conveyerBelt.getCurrentBrokenBlock() < brokenBlockLimit) {
            // 로봇 하역
            if (conveyerBelt.isRobotExistOnBelt()) {
                conveyerBelt.unload();
            }
            // 벨트 회전
            conveyerBelt.changeLoadPosition();
            conveyerBelt.changeUnloadPosition();
            
            // 로봇 하역
            if (conveyerBelt.isRobotExistOnBelt()) {
                conveyerBelt.unload();
            }
            
            // 로봇 이동
            if (conveyerBelt.isRobotExistOnBelt()) {
                conveyerBelt.moveRobot();
            }
            // 로봇 적재
            if (!conveyerBelt.isLoadPositionBroken()) {
                conveyerBelt.loadRobot();
                conveyerBelt.decreaseDurability(conveyerBelt.getLoadPosition());
                conveyerBelt.checkBlockBroken(conveyerBelt.getLoadPosition());
            }
            stage++;
        }
        return stage;
    }

    public static void main(String[] args) {
        input();
        int currentStage = simulate();
        System.out.println(currentStage);
    }
}

class ConveyerBelt {
    private static final int EMPTY = 0;
    private static final int BROKEN = 0;

    private static final int FIRST = 0;
    private static final int CHANGE_POSITION = 0;

    private final int[] durability;
    private int loadPosition;


    private int unloadingPosition;
    private final int beltLength;
    private int brokenBlockCount;

    private final List<Robot> robotExistence;

    public ConveyerBelt(int[] durability, int beltLength) {
        this.durability = durability;
        this.beltLength = beltLength * 2;
        this.loadPosition = CHANGE_POSITION;
        this.unloadingPosition = beltLength - 1;
        this.brokenBlockCount = EMPTY;
        this.robotExistence = new ArrayList<>();
    }

    public int getUnloadingPosition() {
        return unloadingPosition;
    }
    public List<Robot> getRobotExistence() {
        return robotExistence;
    }
    public boolean isLoadPositionBroken() {
        return this.durability[loadPosition] < 1;
    }

    public int getLoadPosition() {
        return this.loadPosition;
    }
    public void getDurability(){

        for (int i = 0; i < this.beltLength; i++) {
            System.out.printf("%3d ", (this.loadPosition+i) % this.beltLength);
        }
        System.out.println();
        for (int i = 0; i < this.beltLength; i++) {
            System.out.printf("%3d ", this.durability[(this.loadPosition+i) % this.beltLength]);
        }
        System.out.println();
    }
    public void loadRobot() {
        robotExistence.add(new Robot(this.loadPosition, robotExistence.size() + 1));
    }

    public void checkBlockBroken(int position) {
        if (durability[position] == BROKEN) {
            this.brokenBlockCount++;
        }
    }

    public int getCurrentBrokenBlock() {
        return this.brokenBlockCount;
    }

    public void decreaseDurability(int position) {
        durability[position] -= 1;
    }

    public void changeLoadPosition() {
        if (this.loadPosition == CHANGE_POSITION) {
            this.loadPosition = this.beltLength - 1;
        } else {
            this.loadPosition -= 1;
        }
    }

    public boolean isRobotExistOnBelt() {
        return !this.robotExistence.isEmpty();
    }

    public void unload() {
        Robot robot = this.robotExistence.get(FIRST);
        if (this.unloadingPosition == robot.getPosition()) {
            robotExistence.remove(FIRST);
        }
    }

    public void changeUnloadPosition() {
        if (this.unloadingPosition == CHANGE_POSITION) {
            this.unloadingPosition = this.beltLength - 1;
        } else {
            this.unloadingPosition -= 1;
        }
    }

    public void moveRobot() {

        for (Robot robot : robotExistence) {
            int positionToMove = (robot.getPosition() + 1) % this.beltLength;
            if (durability[positionToMove] > 0 && !isExistRobotOn(positionToMove)) {
                this.decreaseDurability(positionToMove);
                robot.changePosition(this.beltLength);
                this.checkBlockBroken(positionToMove);
            }
        }
    }

    private boolean isExistRobotOn(int positionToMove) {
        return robotExistence.stream().anyMatch(robot -> robot.getPosition() == positionToMove);
    }

}

class Robot {
    private int position;
    private final int order;

    public Robot(int position, int order) {
        this.position = position;
        this.order = order;
    }

    public int getPosition() {
        return position;
    }

    public int getOrder() {
        return order;
    }

    public void changePosition(int beltLength) {
        this.position = (this.position + 1) % beltLength;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + position +
                ", order=" + order +
                '}';
    }
}