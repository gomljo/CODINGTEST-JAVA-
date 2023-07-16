import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        int[] sequence = new int[N];

        for (int i = 0; i < N; i++) {
            sequence[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()&&sequence[stack.peek()] < sequence[i]) {
                sequence[stack.pop()] = sequence[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            sequence[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(sequence[i]).append(" ");
        }
        System.out.println(sb);
    }
}