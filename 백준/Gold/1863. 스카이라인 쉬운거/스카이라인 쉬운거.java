import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int building = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int location = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek() > height) {
                building++;
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() == height) {
                continue;
            }
            stack.push(height);
        }

        while (!stack.isEmpty()) {
            int h = stack.pop();
            if (h > 0) {
                building++;
            }

        }
        System.out.println(building);
        br.close();
    }
}