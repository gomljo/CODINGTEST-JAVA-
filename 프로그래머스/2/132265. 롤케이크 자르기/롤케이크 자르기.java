import java.util.HashMap;
import java.util.HashSet;

class Solution {

    public int solution(int[] topping) {
        int answer = 0;

        HashMap<Integer, Integer> rightRollCake = new HashMap<>();
        HashMap<Integer, Integer> leftRollCake = new HashMap<>();
        for (int ingredient: topping){
            rightRollCake.put(ingredient, rightRollCake.getOrDefault(ingredient, 0)+1);
        }
        
        for (int ingredient: topping){
            if(rightRollCake.containsKey(ingredient)){
                int numberOfIngredient = rightRollCake.get(ingredient);
                if(numberOfIngredient-1==0){
                    rightRollCake.remove(ingredient);
                }
                else{
                    rightRollCake.put(ingredient, numberOfIngredient-1);
                }
            }
            leftRollCake.put(ingredient, leftRollCake.getOrDefault(ingredient, 0)+1);
            if(rightRollCake.keySet().size()==leftRollCake.keySet().size()){
                answer++;
            }
        }


        return answer;
    }
}