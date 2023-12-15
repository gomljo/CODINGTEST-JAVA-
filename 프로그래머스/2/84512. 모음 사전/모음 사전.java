import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    private static final List<String> dictionary = new ArrayList<>();
    private static final String[] word = new String[]{"A", "E", "I", "O", "U"};
    private static final int MAX_DEPTH = 5;
    public static void makeWord(int depth, List<String> current, boolean[] visited) {

        if(depth==MAX_DEPTH){
            return;
        }

        for (int i = 0; i < word.length; i++) {

            if(!visited[i]){
                current.add(word[i]);
                dictionary.add(String.join("", current));
                makeWord(depth+1, current, visited);
                current.remove(depth);
            }
        }
    }


    public int solution(String word) {
        // 1개로 이루어졌을 때부터 5개로 이루어졌을 때까지 문자열을 만든 뒤에 인덱스로 접근
        List<String> current = new ArrayList<>();
        boolean[] visited = new boolean[MAX_DEPTH];
        makeWord(0, current, visited);
        dictionary.sort(Comparator.naturalOrder());
        return dictionary.indexOf(word)+1;
    }
    
}