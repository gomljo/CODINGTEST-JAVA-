import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
//        int[] numbers = {2, 3, 3, 5};
        int[] numbers = {9, 1, 5, 3, 6, 2};
//        int[] numbers = {9, 5, 7, 4, 5, 3, 6, 2};
        Solution solution = new Solution();
        int[] answer = solution.solution(numbers);
        System.out.println(Arrays.toString(answer));
    }

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int end = numbers.length - 1;
        answer[end] = -1;
        HashMap<Integer, Integer> behind = new HashMap<>();
        int curMax = numbers[end];
        behind.put(numbers[end], 0);

        for (int i = end-1; i >= 0; i--) {
           if(behind.containsKey(numbers[i])){
               if(curMax <= behind.get(numbers[i])){
                   answer[i] = behind.get(numbers[i]);
               }
               else {
                    behind.put(numbers[i], curMax);
                    answer[i] = curMax;
               }
           }
           else {
               answer[i] = curMax;
               if(curMax <= numbers[i]){
                   curMax = numbers[i];
                   answer[i] = -1;
               }
               behind.put(numbers[i], curMax);
           }
            if(i-1 > -1 && numbers[i-1] < numbers[i]){
                curMax = numbers[i];
            }
        }
        return answer;
    }
}