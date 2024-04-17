import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        int[] possibleCases = new int[n.length() + 1];
        possibleCases[0] = 1;
        for (int i = 1; i <= n.length(); i++) {
            if(n.charAt(i-1)!='0'){
                possibleCases[i] = (possibleCases[i] + possibleCases[i - 1]) % 1000000;
            }
            if (i - 2 >= 0) {
                String twoDigit = n.substring(i - 2, i);
                if(twoDigit.startsWith("0") || Integer.parseInt(twoDigit) > 26){
                    continue;
                }
                possibleCases[i] = (possibleCases[i] + possibleCases[i - 2]) % 1000000;
            }
        }
        System.out.println(possibleCases[n.length()]);
        scanner.close();
    }
}