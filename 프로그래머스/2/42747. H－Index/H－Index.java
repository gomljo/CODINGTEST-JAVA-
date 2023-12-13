import java.util.Arrays;

public class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        int maxCitations = citations[citations.length-1];
        for (int i = 0; i < maxCitations; i++) {
            int numberOfPaper = 0;
            for (int j = 0; j < citations.length; j++) {
                if (i <= citations[j]) {
                    numberOfPaper++;
                }
            }
            if (i <= numberOfPaper && citations.length - numberOfPaper <= i) {
                answer = i;
            }
        }


        return answer;
    }
}