import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] budgets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int totalBudgets = Integer.parseInt(br.readLine());
        UpperBoundBudgetFinder finder = new UpperBoundBudgetFinder(budgets, totalBudgets);
        finder.find();
        finder.printActualBudget();
        br.close();
    }
}

class UpperBoundBudgetFinder {
    private final int[] budgets;
    private final int totalBudgets;
    private int actualBudget;

    public UpperBoundBudgetFinder(int[] budgets, int totalBudgets) {
        this.budgets = budgets;
        this.totalBudgets = totalBudgets;
    }

    public void find() {
        Arrays.sort(this.budgets);
        int start = 1;
        int end = this.budgets[this.budgets.length-1];
        int maxBudget = 0;
        while (start <= end) {
            maxBudget = (start + end) / 2;
            int newTotalBudget = calculateTotalBudgets(maxBudget);

            if (newTotalBudget <= this.totalBudgets) {
                start = maxBudget + 1;
            } else {
                end = maxBudget - 1;
            }
        }
        this.actualBudget = end;
    }

    private int calculateTotalBudgets(int maxBudget) {
        int newTotal = 0;
        for (int budget : this.budgets) {
            newTotal += Math.min(budget, maxBudget);
        }
        return newTotal;
    }

    public void printActualBudget(){
        System.out.println(this.actualBudget);
    }
}