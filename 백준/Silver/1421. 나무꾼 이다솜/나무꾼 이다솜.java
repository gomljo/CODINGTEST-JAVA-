import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfWood = Integer.parseInt(st.nextToken());
        long priceForCutting = Integer.parseInt(st.nextToken());
        long pricePerUnit = Integer.parseInt(st.nextToken());

        int[] woods = new int[numberOfWood];
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < numberOfWood; i++) {
            woods[i] = Integer.parseInt(br.readLine());
            maxLength = Math.max(woods[i], maxLength);
        }
        long maxPrice = Long.MIN_VALUE;
        for (long woodLength = 10000; woodLength > 0; woodLength--) {
            long currentTotalPrice = 0;
            for (int wood: woods) {
                long currentPieces = wood / woodLength;
                long currentCutting = wood / woodLength - 1;
                long remainWood = wood % woodLength;
                if (remainWood > 0){
                    currentCutting += 1;
                }
                long currentPrice = currentPieces * woodLength * pricePerUnit - currentCutting * priceForCutting;
                if(currentPrice> 0){
                    currentTotalPrice += currentPrice;
                }

            }
            maxPrice = Math.max(currentTotalPrice, maxPrice);
        }
        System.out.println(maxPrice);
    }
}