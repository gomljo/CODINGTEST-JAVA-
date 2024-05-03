import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = scanner.nextInt();
        }
        HeightJudgeSimulator simulator = new HeightJudgeSimulator(n, order);
        simulator.initialize();
        simulator.simulate();
        simulator.printResult();

        scanner.close();
    }
}

class HeightJudgeSimulator {
    private final int[] order;
    private int[] height;
    private int n;

    public HeightJudgeSimulator(int n, int[] order) {
        this.order = order;
        this.height = new int[n];
        this.n = n;
    }

    public void initialize() {
        for (int i = 0; i < this.n; i++) {
            this.height[i] = this.n - i;
        }
    }

    public void simulate() {
        for (int i = this.order.length - 1; i >= 0; i--) {
            int numberOfTallerPeople = countTallerPeopleOnLeftSideWith(this.order.length - 1 - i);
            changePosition(this.order.length - 1 - i, numberOfTallerPeople, this.order[i]);
        }
    }

    private void changePosition(int index, int current, int target) {

        for (int i = index; i >= 0; i--) {
            if (this.height[index] < this.height[i]) {
                current--;
            }
            if (current == target) {
                for (int j = index; j > i; j--) {
                    int temp = this.height[j];
                    this.height[j] = this.height[j - 1];
                    this.height[j - 1] = temp;
                }
                break;
            }
        }
    }

    private int countTallerPeopleOnLeftSideWith(int index) {
        int count = 0;
        for (int i = index; i >= 0; i--) {
            if (this.height[index] < this.height[i]) {
                count++;
            }
        }

        return count;
    }

    public void printResult() {
        for (int h : height) {
            System.out.print(h + " ");
        }
        System.out.println();
    }
}