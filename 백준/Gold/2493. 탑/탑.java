import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTower = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int[] tower = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int[] receive = new int[numberOfTower];
        for (int i = 0; i < numberOfTower; i++) {
            int height = tower[i];

            while (!stack.isEmpty() && tower[stack.peek()] < height) {
                stack.pop();
            }
            if (!stack.isEmpty() && tower[stack.peek()] > height) {
                receive[i] = stack.peek()+1;
            } else {
                receive[i] = 0;
            }
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < receive.length; i++) {
            sb.append(receive[i] + " ");
        }
        System.out.println(sb);

        br.close();
    }
}