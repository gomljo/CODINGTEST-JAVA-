import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ppap = br.readLine().split("");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < ppap.length; i++) {

            if(stack.size() >= 4 && isPPAP(stack)){
                for (int j = 0; j < 4; j++) {
                    stack.pop();
                }
                stack.push("P");
            }
            stack.push(ppap[i]);
        }
        if(stack.size()==4 && isPPAP(stack)){
            System.out.println("PPAP");
        } else if (stack.size()==1 && stack.peek().equals("P")) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
    public static boolean isPPAP(Stack<String> stack){
        return stack.get(stack.size()-4).equals("P")
                && stack.get(stack.size()-3).equals("P")
                && stack.get(stack.size()-2).equals("A")
                && stack.get(stack.size()-1).equals("P");
    }
}