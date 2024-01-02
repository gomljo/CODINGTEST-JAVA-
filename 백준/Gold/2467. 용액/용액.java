import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] liquid = new int[n];

        for (int i = 0; i < n; i++) {
            liquid[i] = sc.nextInt();
        }

        int start = 0;
        int end = liquid.length - 1;
        int min = Integer.MAX_VALUE;
        int acid = 0;
        int alkali = 0;
        while (start < end) {
            int sum = Math.abs(liquid[start] + liquid[end]);
            if(sum <= min){
                acid = liquid[start];
                alkali = liquid[end];
                min = sum;
            }
            if (Math.abs(liquid[start + 1] + liquid[end]) < sum) {
                start++;
            } else if (Math.abs(liquid[start] + liquid[end-1]) < sum) {
                end--;
            }
            else {
                start++;
                end--;
            }
        }
        System.out.printf("%d %d\n", acid, alkali);

    }
}