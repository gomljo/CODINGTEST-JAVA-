import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long d = Integer.parseInt(st.nextToken());
        Rule[] rules = new Rule[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int interval = Integer.parseInt(st.nextToken());
            rules[i] = new Rule(start, end, interval);
        }
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = (start + end) / 2;
            long res = addCount(mid, k, rules);
            if (res >= d) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
        br.close();
    }
    public static long addCount(int mid, int k, Rule[] rules){
        long cnt = 0;
        for (int i = 0; i < k; i++) {
            if(mid < rules[i].getStart()){
                continue;
            }
            int end = Math.min(rules[i].getEnd(), mid);
            cnt += (end - rules[i].getStart()) / rules[i].getInterval() + 1;
        }
        return cnt;
    }
}
class Rule {
    private final int start;
    private final int end;
    private final int interval;

    public Rule(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getInterval() {
        return interval;
    }
}