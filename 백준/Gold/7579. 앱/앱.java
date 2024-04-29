import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_VALUE = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] apps = new int[n];
        int[] activateCost = new int[n];

        // 차지하고 있는 메모리 용량 초기화
        st = new StringTokenizer(br.readLine());
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            apps[i] = Integer.parseInt(st.nextToken());
        }
        // 활성화 비용 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            activateCost[i] = Integer.parseInt(st.nextToken());
            totalCost+=activateCost[i];
        }
        int[] cost = new int[10_000_000];
        for (int i = 0; i < n; i++) {
            for (int k = totalCost; k >= activateCost[i]; k--) {
                cost[k] = Math.max(cost[k], cost[k - activateCost[i]] + apps[i]);
            }
        }
        int ans=0;
        for(int i=0; i<=totalCost; i++) {
            if(cost[i]>=m) {
                ans=i;
                break;
            }
        }

        System.out.println(ans);

        br.close();
    }
}