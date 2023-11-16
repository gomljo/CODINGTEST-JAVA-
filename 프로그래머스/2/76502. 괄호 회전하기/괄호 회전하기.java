

import java.util.HashMap;
import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        char[] parenthesis = s.toCharArray();
        HashMap<Character, Character> pair = new HashMap<>();
        for (int i = 0; i < parenthesis.length; i++) {
            Stack<Character> stack = new Stack<>();
            int idx = i;
            int cnt = 0;
            while (cnt < parenthesis.length){
                if(stack.isEmpty()){
                    stack.push(parenthesis[idx]);
                }else{
                    if(stack.peek() == '(' && parenthesis[idx] == ')'){
                        stack.pop();
                    } else if (stack.peek() == '{' && parenthesis[idx] == '}') {
                        stack.pop();
                    } else if (stack.peek() == '[' && parenthesis[idx] == ']') {
                        stack.pop();
                    } else {
                        stack.push(parenthesis[idx]);
                    }
                }
                cnt++;
                idx = (idx+1) % parenthesis.length;
            }
            if(stack.isEmpty()){
                answer++;
            }
        }

        return answer;
    }
}
