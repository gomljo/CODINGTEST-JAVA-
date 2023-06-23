package org.example;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        Deque<String> card1 = new LinkedList<>(Arrays.asList(cards1));
        Deque<String> card2 = new LinkedList<>(Arrays.asList(cards2));

        for (String word: goal){
            if (!card1.isEmpty() && word.equals(card1.peek())){
                card1.pollFirst();
            }
            else if(!card2.isEmpty() && word.equals(card2.peek())){
                card2.pollFirst();
            }
            else {
                answer = "No";
                break;
            }
        }
        return answer;
    }
}
