import java.util.Scanner;

public class Main {
    // 이분 탐색
    // n명의 사람이 m개의 입국 심사대를 거쳐야한다.
    // m개의 입국 심사대는 각각 입국 심사에 걸리는 시간이 상이하다.
    // 걸리는 시간을 매개 변수로 두고
    // 모든 사람이 해당 시간에 입국 심사를 통과할 수 있는지 확인
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfImmigrationCheckpoint = scanner.nextInt();
        int numberOfPeople = scanner.nextInt();
        long[] immigrationCheckpoint = new long[numberOfImmigrationCheckpoint];
        long maxTime = 0L;
        for (int i = 0; i < numberOfImmigrationCheckpoint; i++) {
            immigrationCheckpoint[i] = scanner.nextLong();
            maxTime = Math.max(maxTime, immigrationCheckpoint[i]);
        }
        ImmigrationCheckSimulator simulator = new ImmigrationCheckSimulator(numberOfPeople, maxTime, immigrationCheckpoint);
        simulator.simulate();
        simulator.printResult();
        scanner.close();
    }
}

class ImmigrationCheckSimulator {
    private final int numberOfPeople;
    private final long[] immigrationCheckpoint;
    private final long maxTime;
    private long timeToCheck;

    public ImmigrationCheckSimulator(int numberOfPeople, long maxTime, long[] immigrationCheckpoint) {
        this.numberOfPeople = numberOfPeople;
        this.maxTime = maxTime;
        this.immigrationCheckpoint = immigrationCheckpoint;
        this.timeToCheck = 0L;
    }

    public void simulate() {
        long minimumTime = 0;
        long maximumTime = this.maxTime * this.numberOfPeople;

        while (minimumTime < maximumTime) {
            long half = (minimumTime + maximumTime) / 2;
            long count = calculate(half);
            if (count >= this.numberOfPeople) {
                maximumTime = half;
            } else {
                minimumTime = half + 1;
            }
        }
        this.timeToCheck = minimumTime;

    }

    private long calculate(long totalTime) {
        long count = 0;
        for (long time : this.immigrationCheckpoint) {
            if(this.numberOfPeople < count){
                break;
            }
            count += totalTime / time;
        }
        return count;
    }

    public void printResult() {
        System.out.println(this.timeToCheck);
    }
}