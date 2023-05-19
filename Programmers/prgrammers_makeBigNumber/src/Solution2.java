import java.util.*;
import java.util.stream.Collectors;

class Solution2 {
    public String solution(String number, int k) {
        String[] numbers = number.split("");
        int wantedDigit = number.length()-k;
        int[] maxNumber = new int[wantedDigit];
        int numOfDeleted = 0;
        int top = -1;

        for (int i = 0; i < numbers.length; i++) {
            int num = Integer.parseInt(numbers[i]);
            if(top==-1){
                top++;
                maxNumber[top] = num;
                continue;
            }
            if(numbers.length - i > wantedDigit && maxNumber[top] < num){
                while (maxNumber[top] < num && numOfDeleted < k){
                    maxNumber[top--] = 0;
                    numOfDeleted++;
                }
                maxNumber[top] = num;
            } else if (maxNumber[top] >= num) {
                if(top+1 < wantedDigit){
                    maxNumber[++top] = num;
                }
            }
        }
        System.out.println(Arrays.toString(maxNumber));
        return "";
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        String answer = solution.solution("1924", 2);
//       String answer =  solution.solution("1231234", 3);
//        String answer = solution.solution(	"4177252841", 4);
//        String answer = solution.solution(	"41", 1);
//        System.out.println(answer);
    }

}