import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int idx = 0;

        for (int i = 0; i < prices.length-1; i++) {
            answer[i] = 1;
        }
        
        while (idx < prices.length-1){
            int value = prices[idx];
            int next = idx+1;
            while (next < prices.length-1 && value <= prices[next]){
                answer[idx]+=1;
                next++;
            }
            idx++;
        }
        return answer;
    }
}