import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfPeople = sc.nextInt();
        int[] numberOfUse = new int[100_001];
        PriorityQueue<ComputerUsingTime> computerUsingTimePriorityQueue =
                new PriorityQueue<>(Comparator.comparing(ComputerUsingTime::getStart)
                        .thenComparing(ComputerUsingTime::getEnd));
        for (int i = 0; i < numberOfPeople; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            computerUsingTimePriorityQueue.add(new ComputerUsingTime(start, end));
        }
        PriorityQueue<Computer> endTimePriorityQueue = new PriorityQueue<>(
                Comparator.comparing(Computer::getEnd));
        PriorityQueue<Integer> indexPriorityQueue = new PriorityQueue<>();
        int count = 0;
        while (!computerUsingTimePriorityQueue.isEmpty()) {
            ComputerUsingTime computerUsingTime = computerUsingTimePriorityQueue.poll();
            while (!endTimePriorityQueue.isEmpty() && computerUsingTime.getStart() > endTimePriorityQueue.peek().getEnd()) {
               indexPriorityQueue.add(endTimePriorityQueue.poll().getIndex());
            }

            if(indexPriorityQueue.isEmpty()){
                endTimePriorityQueue.add(new Computer(count, computerUsingTime.getStart(), computerUsingTime.getEnd()));
                numberOfUse[count]++;
                count++;
            }
            else {
                endTimePriorityQueue.add(new Computer(indexPriorityQueue.peek(),
                        computerUsingTime.getStart(), computerUsingTime.getEnd()));
                numberOfUse[indexPriorityQueue.poll()]++;
            }
        }
        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(numberOfUse[i]).append(" ");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}

class Computer {
    private final int index;
    private final int start;
    private final int end;

    public Computer(int index, int start, int end) {
        this.start = start;
        this.end = end;
        this.index = index;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getIndex() {
        return index;
    }
}

class ComputerUsingTime {
    private final int start;
    private final int end;

    public ComputerUsingTime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}