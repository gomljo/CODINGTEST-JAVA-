import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        int same = 0;
        int diff = 0;
        String standard = "";
        Queue<String> queue = new LinkedList<>(Arrays.asList(s.split("")));

        while (!queue.isEmpty()){
            String word = queue.poll();
            if(standard.equals("")){
                standard = word;
                same +=1;
            }
            else if(standard.equals(queue.peek())){
                
            }

        }

        return answer;
    }
}
