import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = Math.abs(numbers[i] - numbers[i + 1]);
        }
        int gcd = diff[0];
        for (int i = 1; i < n-1; i++) {
            gcd = gcd(diff[i], gcd);
        }
        for (int i = 2; i <= gcd; i++) {
            if(gcd % i==0){
                System.out.println(i);
            }
        }
    }
}