import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> priorityQueue = Arrays.stream(scoville).boxed().collect(Collectors.toCollection(PriorityQueue::new));

        while (true) {
            int food1=0;
            int food2=0;
            if (!priorityQueue.isEmpty() && priorityQueue.peek() < K){
                food1 = priorityQueue.poll();
            }
            else {
                break;
            }
            if (!priorityQueue.isEmpty()){
                food2 = priorityQueue.poll();
            }
            else {
                break;
            }
            food1 = food1 + food2 * 2;
            priorityQueue.add(food1);
            answer++;

        }

        if(priorityQueue.isEmpty()){
            return -1;
        }


        return answer;
    }
}