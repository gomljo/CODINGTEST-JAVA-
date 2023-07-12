import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] sequence = new int[N];

        for (int i = 0; i < N; i++) {
            sequence[i] = scanner.nextInt();
        }

        int number=1;
        int idx = 0;
        List<Integer> sequenceByStack = new ArrayList<>();
        List<String> operations = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            if(sequence[idx]!=i){
                stack.push(i);
                operations.add("+");
            }
            else {
                stack.push(i);
                operations.add("+");
                while (!stack.isEmpty()){
                    if(sequence[idx]==stack.peek()) {
                        sequenceByStack.add(stack.pop());
                        operations.add("-");
                        idx++;

                    }
                    else {
                        break;
                    }
                }
            }
        }
        if(sequenceByStack.size()==N){
            System.out.println(String.join("\n", operations));
        }
        else {
            System.out.println("NO");
        }

    }
}