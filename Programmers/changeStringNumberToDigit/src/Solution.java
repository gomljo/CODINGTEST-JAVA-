import java.util.HashMap;

class Solution {

    public static HashMap<String, Integer> init(){
        HashMap<String, Integer> dict = new HashMap<>();
        String[] word = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] number = {0,1,2,3,4,5,6,7,8,9};

        for (int i = 0; i < word.length; i++) {
            dict.put(word[i], number[i]);
        }
        return dict;
    }


    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        HashMap<String, Integer> dictionary = init();
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        for (char c : arr) {

            if (Character.isAlphabetic(c)) {
                sb.append(c);

                if (dictionary.containsKey(sb.toString())) {
                    answer.append(dictionary.get(sb.toString()));
                    sb = new StringBuilder();
                }
            } else if (Character.isDigit(c)) {
                answer.append(c);
                sb = new StringBuilder();
            }
        }
        return Integer.parseInt(answer.toString());
    }
}