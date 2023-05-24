import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String number, int k) {
        String answer = "";

    Queue<Integer> queue = Arrays.stream(number.split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toCollection(LinkedList::new));
        int wantedDigit = number.length()-k;
        String[] maxNumber = new String[wantedDigit];
        int numOfDeleted = 0;
        Stack<Integer> stack = new Stack<>();
        while (stack.size() + queue.size() != wantedDigit){
            int num = queue.poll();
            System.out.println("before operation");
            System.out.println("num = " + num);
            System.out.println("stack = " + stack);
            System.out.println("queue = " + queue);
            System.out.println();

            if(stack.isEmpty() && !queue.isEmpty()){
                stack.push(num);
                continue;
            }

            if(stack.peek() < num){
                System.out.println("top element is less than number");

                while (!stack.isEmpty() && numOfDeleted < k){
                    if(stack.peek() < num){
                        stack.pop();
                        numOfDeleted++;
                    }
                    else {
                        break;
                    }
                    System.out.println("numOfDeleted = " + numOfDeleted);
                    System.out.println("stack = " + stack);
                }
                stack.push(num);
            }
            else {
                if(stack.size() < wantedDigit){
                    stack.push(num);
                }
            }
            System.out.println("after operation");
            System.out.println("stack = " + stack);
            System.out.println("queue = " + queue);
            System.out.println();
        }
        System.out.println("after while");
        System.out.println("stack = " + stack);
        System.out.println("queue = " + queue);

        String prefix = stack.stream().map(String::valueOf).collect(Collectors.joining(""));
        String postfix = queue.stream().map(String::valueOf).collect(Collectors.joining(""));

        return prefix+postfix;
    }
}

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
//        String answer = solution.solution("1924", 2);
//       String answer =  solution.solution("1231234", 3);
        String answer = solution.solution(	"4177252841", 4);
//        String answer = solution.solution(	"41", 1);
        System.out.println(answer);
    }
}