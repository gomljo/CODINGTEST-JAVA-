import java.util.HashMap;
import java.util.Map;


public class Solution {

    public int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] cloth :clothes) {
            String category = cloth[1];
            if(!map.containsKey(category)){
                map.put(category, 1);
            }
            else {
                int cnt = map.get(category);
                map.put(category, cnt+1);
            }
        }
        int possible = 1;
        for (Map.Entry<String, Integer> s: map.entrySet()) {
            possible *= s.getValue()+1;
        }

        return possible-1;
    }


}
