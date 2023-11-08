

import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<Integer, HashMap<String, Integer>> candidates = new HashMap<>();

        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> orderedWants = new HashMap<>();
            for (int j = 0; j < want.length; j++) {
                orderedWants.put(want[j], 0);
                candidates.put(i, orderedWants);
            }
        }

        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> candidate = candidates.get(i);
            for (int j = 0; j < 10; j++) {
                candidate.put(discount[i+j], candidate.getOrDefault(discount[i+j], 0) + 1);
            }
        }
        for (int i = 0; i < candidates.size(); i++) {
            HashMap<String, Integer> candidate = candidates.get(i);
            boolean isPossible = true;
            for (int j = 0; j < want.length; j++) {
                if (candidate.get(want[j]) != number[j]) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                answer += 1;
            }

        }
        return answer;
    }
}
