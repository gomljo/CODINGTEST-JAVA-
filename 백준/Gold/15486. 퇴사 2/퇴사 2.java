import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Consulting[] consultingSchedule = new Consulting[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int timeConsumption = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            consultingSchedule[i] = new Consulting(timeConsumption, profit);
        }
        int[] profits = new int[n + 1];
        Arrays.fill(profits, 0);
        for (int i = 1; i <= n; i++) {
            if (profits[i] < profits[i - 1]) {
                profits[i] = profits[i - 1];
            }
            int timeConsumption = consultingSchedule[i].getTimeConsumption();
            int profit = consultingSchedule[i].getProfit();
            if(i+timeConsumption-1 <=n){
                profits[i + consultingSchedule[i].getTimeConsumption() - 1] = Math.max(profits[i + timeConsumption - 1], profits[i - 1] + profit);
            }
        }
        System.out.println(profits[n]);

        br.close();
    }
}

class Consulting {
    private final int timeConsumption;
    private final int profit;

    public Consulting(int timeConsumption, int profit) {
        this.timeConsumption = timeConsumption;
        this.profit = profit;
    }

    public int getTimeConsumption() {
        return timeConsumption;
    }

    public int getProfit() {
        return profit;
    }
}