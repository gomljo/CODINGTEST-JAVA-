import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int num = scanner.nextInt();
            if(num!=0){
                stack.push(num);
            }
            else {
                stack.pop();
            }
        }
        int sum = 0;
        while (!stack.isEmpty()){
            sum+=stack.pop();
        }
        System.out.println(sum);
    }
}