import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.next();
        String bomb = sc.next();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sentence.length(); i++) {
            stack.push(sentence.charAt(i));

            if (stack.size() >= bomb.length()) {
                boolean isExploded = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        isExploded = false;
                        break;
                    }
                }
                if (isExploded) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }


        }
        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }
        System.out.println(sb.length()==0? "FRULA" : sb.toString());

    }

}