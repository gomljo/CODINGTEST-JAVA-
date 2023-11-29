import java.util.ArrayList;
import java.util.List;
class Solution {
    public static String convertToNDigit(int n, int number){
        return Integer.toString(number, n).toUpperCase();
    }

    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        List<Integer> wantIndexes = new ArrayList<>();
        // 원하는 위치들을 구함
        for (int i = 0; i < t; i++) {
            // m명의 사람이 t번 반복하고 인덱스는 p-1번이므로
            wantIndexes.add(m*i+p-1);
        }
        int num = 0;
        int max = wantIndexes.get(wantIndexes.size()-1)+1;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < max){
           String digit = convertToNDigit(n, num++);
           sb.append(digit);
        }
        String[] numbers = sb.toString().split("");
        for (Integer wantIndex : wantIndexes) {
            answer.append(numbers[wantIndex]);
        }
        
        return answer.toString();
    }
}