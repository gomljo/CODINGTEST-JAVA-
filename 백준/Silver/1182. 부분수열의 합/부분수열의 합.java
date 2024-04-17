import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }
        PossibilitySimulator possibilitySimulator = new PossibilitySimulator(sequence, s);
        possibilitySimulator.simulate(0, 0, 0);
        System.out.println(possibilitySimulator.getNumberOfPossibleCases());
        scanner.close();
    }
}

class PossibilitySimulator {
    private final int[] sequence;
    private final int target;
    private int numberOfPossibleCases;

    public PossibilitySimulator(int[] sequence, int target) {
        this.sequence = sequence;
        this.target = target;
        this.numberOfPossibleCases = 0;
    }

    public void simulate(int depth, int number, int select) {
        if (depth == this.sequence.length) {
            if (select == 0) {
                return;
            }
            if(number==this.target){
                this.numberOfPossibleCases++;
            }
            return;
        }
        simulate(depth + 1, number, select);
        simulate(depth + 1, number + this.sequence[depth], select + 1);
    }

    public int getNumberOfPossibleCases() {
        return this.numberOfPossibleCases;
    }

}