import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0,0};
        int order = 0;
        int person = 0;

        List<String> pastWords = new ArrayList<>();
        pastWords.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            person = i % n + 1;
            order = i / n + 1;
            String pastWord = pastWords.get(i-1);
            if(pastWords.contains(words[i]) || !words[i].startsWith(pastWord.substring(pastWord.length()-1))){
                answer = new int[]{person, order};
                break;
            }
            pastWords.add(words[i]);
        }

        return answer;
    }
}