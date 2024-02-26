import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] array = new long[n];
        long count = 0;
        long[] reminder = new long[1000];
        array[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            int number = sc.nextInt();
            array[i] = array[i-1] + number;
        }
        for (int i = 0; i < n; i++) {
            long rem = array[i] % m;

            if(rem==0){
                count++;
            }
            reminder[(int)rem]++;
        }
        for (int i = 0; i < reminder.length; i++) {
            if (reminder[i] > 0) {
                count += (reminder[i] * (reminder[i] - 1)) / 2;
            }
        }
        System.out.println(count);

    }
}