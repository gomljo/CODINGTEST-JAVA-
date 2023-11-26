import java.util.*;
class Solution {
    public int[] solution(String msg) {
        int[] answer = {};

        char[] letters = msg.toCharArray();
        LinkedHashMap<String, Integer> dictionary = new LinkedHashMap<>();

        for (int i = 0; i < 26; i++) {
            dictionary.put(String.valueOf((char) ('A' + i)), i + 1);
        }
        int start = 0;
        int end = 1;
        List<Integer> LZW = new ArrayList<>();
        int cnt = 0;
        while (cnt < letters.length) {

            while (end <= letters.length) {
                if (dictionary.containsKey(msg.substring(start, end))) {
                    end += 1;
                } else {
                    break;
                }
            }
            int compress;
            int index = dictionary.keySet().size() + 1;
            if (end <= msg.length()) {
                compress = dictionary.get(msg.substring(start, end - 1));
                dictionary.put(msg.substring(start, end), index);
            } else {
                compress = dictionary.get(msg.substring(start));

                dictionary.put(msg.substring(start), index);
            }

            LZW.add(compress);
            cnt += Math.abs(start - (end - 1));
            start = end - 1;
            end = start + 1;
        }

        return LZW.stream().mapToInt(i->i).toArray();
    }
}