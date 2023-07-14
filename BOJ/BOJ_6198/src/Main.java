import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] buildingHeights = new int[N];
        for (int i = 0; i < N; i++) {
            buildingHeights[i] = scanner.nextInt();
        }
        long answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && buildingHeights[stack.peek()] <= buildingHeights[i]){
                stack.pop();
            }
            answer += stack.size();
            stack.push(i);
        }

        System.out.println(answer);
    }
}