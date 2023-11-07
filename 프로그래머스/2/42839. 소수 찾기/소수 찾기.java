import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private static String[] numberList;
    private static final Set<Integer> numberSet = new HashSet<>();
    public static boolean isPrimeNumber(int number){

        if(number < 2){
            return false;
        }
        int half = (int)Math.sqrt(number);

        for (int i = 2; i <= half ; i++) {
            if(number % i==0){
                return false;
            }
        }
        return true;
    }

    public static void makeNumbers(int currentDepth, int maxDepth, String number, boolean[] visited) {
        if(currentDepth > maxDepth){
            return;
        }
        numberSet.add(Integer.parseInt(number));
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                System.out.println(number+numberList[i]);
                makeNumbers(currentDepth+1, maxDepth, number+numberList[i], visited);
                visited[i] = false;
            }
        }


    }

    public int solution(String numbers) {
        int answer = 0;
        numberList = numbers.split("");
        // 주어진 numbers의 길이는 1이상 7이하
        // 가장 긴 경우인 7을 가정하면 총 7 X 6 X 5 X 4 X 3 X 2 X 1 = 5040 가지가 나온다.
        // 그러므로 조합을 이용하여 풀 수 있을 것 같다.
        boolean[] visited = new boolean[numbers.length()];

        makeNumbers(0, numbers.length(), "0", visited);

        List<Integer> numberList = new ArrayList<>(numberSet);

        for (Integer integer : numberList) {
            if (isPrimeNumber(integer)) {
                answer++;
            }
        }
        System.out.println(answer);
        return answer;
    }

}
