import java.util.HashMap;

public class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, String> map = new HashMap<>();
        
        for (String phoneNumber: phone_book){
            map.put(phoneNumber, phoneNumber);
        }
        for (String key: map.keySet()) {
            String phoneNumber = map.get(key);
            for (int i = 1; i < phoneNumber.length(); i++) {
                if(map.containsKey(phoneNumber.substring(0,i))){
                    return false;
                }
            }
            map.put(phoneNumber, phoneNumber);
        }
        return answer;
    }
}