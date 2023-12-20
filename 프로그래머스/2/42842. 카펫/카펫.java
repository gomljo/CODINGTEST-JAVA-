public class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for (int horizon = 3; horizon <brown ; horizon++) {
            for (int vertical = 3; vertical <brown ; vertical++) {
                
                if(((horizon-2) * (vertical-2)==yellow) && horizon>=vertical && ((horizon-2) * 2+ vertical * 2==brown)){
                    answer[0] = horizon;
                    answer[1] = vertical;
                }
            }
        }

        return answer;
    }
}