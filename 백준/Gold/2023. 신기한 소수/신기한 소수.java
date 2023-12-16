import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int MAX_DEPTH;
    private static final List<String> amazingNumber = new ArrayList<>();

    public static boolean isPrimeNumber(int number) {

        if(number < 2){
            return false;
        }

        if (number==2){
            return true;
        }

        for (int i = 2; i < Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void makeAmazingNumber(int depth, String number) {

        if (depth == MAX_DEPTH) {
            amazingNumber.add(number);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isPrimeNumber(Integer.parseInt(number + i))) {
                makeAmazingNumber(depth + 1, number + i);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MAX_DEPTH = sc.nextInt();
        makeAmazingNumber(0, "");
        System.out.println(String.join("\n",amazingNumber));
    }
}