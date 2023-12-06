public class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int unit = 1;
        if(s.length()==1) {
            return 1;
        }
        while (unit < s.length()){
            String compare = s.substring(0, unit);
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i+=unit) {
                String key = s.substring(i, Math.min(i+unit, s.length()));
                if(!compare.equals(key)){
                    if(cnt < 2){
                        sb.append(compare);
                    }
                    else {
                        sb.append(cnt);
                        sb.append(compare);
                    }
                    compare = key;
                    cnt = 1;
                    continue;
                }
                cnt++;
            }
            if(cnt < 2){
                sb.append(compare);
            }
            else {
                sb.append(cnt);
                sb.append(compare);
            }
            answer = Math.min(answer, sb.length());
            unit++;
        }
        return answer;
    }

}
