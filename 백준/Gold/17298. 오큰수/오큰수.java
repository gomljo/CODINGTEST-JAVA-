import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] seq = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && seq[i] > seq[stack.peek()]){
                seq[stack.pop()] = seq[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            seq[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(seq[i]).append(' ');
        }

        System.out.println(sb);
        sc.close();
    }
}