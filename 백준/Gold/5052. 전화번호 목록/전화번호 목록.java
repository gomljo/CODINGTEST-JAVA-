import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTest = scanner.nextInt();

        for (int i = 0; i < numberOfTest; i++) {
            HashMap<String, String> phoneBook = new HashMap<>();
            int numberOfPhoneNumber = scanner.nextInt();
            String[] phoneNumbers = new String[numberOfPhoneNumber];
            for (int j = 0; j < numberOfPhoneNumber; j++) {
                phoneNumbers[j] = scanner.next();
            }
            Arrays.sort(phoneNumbers);
            if (isConsistent(phoneNumbers)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }


        }

        scanner.close();
    }

    public static boolean isConsistent(String[] phoneNumbers) {
        for (int i = 0; i < phoneNumbers.length - 1; i++) {
            if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                return false;
            }
        }
        return true;
    }
}