import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;

        Queue<Integer> queue = Arrays.stream(section).boxed().collect(Collectors.toCollection(LinkedList::new));

        while (!queue.isEmpty()){
            int startUnit = queue.poll();
            int endUnit = startUnit + m - 1;
            while (!queue.isEmpty() && queue.peek() < endUnit){
                queue.poll();
            }
            answer+=1;
        }

        return answer;
    }
}