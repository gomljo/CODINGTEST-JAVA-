import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        LinkedList<Integer> answer = new LinkedList<>();

        LinkedList<Integer> durations = new LinkedList<>();
        int cnt = 1;

        for (int i = 0; i < progresses.length; i++) {
            int left = (100-progresses[i]) % speeds[i];

            int duration;
            if(left==0){
                duration = (100-progresses[i]) / speeds[i];
            }
            else {
                duration = (100-progresses[i]) / speeds[i]+1;
            }

            if(durations.size()==0){
                durations.offerLast(duration);
                answer.offerLast(cnt);
            }
            else if (durations.peekLast() < duration) {
                answer.offerLast(cnt);

                durations.pollLast();
                durations.offerLast(duration);
            }
            else {
                int numOfPublish = answer.pollLast();
                answer.offerLast(numOfPublish + 1);
            }
            System.out.println("durations = " + durations);
        }
        System.out.println("answer = " + answer);
        return answer.stream()
                .filter(i -> i != null)
                .mapToInt(i -> i)
                .toArray();
    }

}
