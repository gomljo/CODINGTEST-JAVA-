import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        String[] split = s.split("");
        Stack<String> stack = new Stack<>();
        for (String value : split) {

            if (stack.isEmpty()) {
                stack.push(value);
                continue;
            }

            if (stack.peek().equals(value)) {
                stack.pop();
            }
            else{
                stack.push(value);
            }
        }

        if(stack.isEmpty()){
            return 1;
        }

        return answer;
    }
}