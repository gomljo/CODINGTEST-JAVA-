import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String target = sc.next();
        StringBuilder input = new StringBuilder(sc.next());
        int answer = 0;
        while (input.length() > target.length()) {
            if (input.lastIndexOf("A") == input.length()-1) {
                input.deleteCharAt(input.length() - 1);
            } else if (input.lastIndexOf("B") == input.length()-1) {
                input.deleteCharAt(input.length() - 1);
                input.reverse();
            }

            if(input.toString().equals(target)){
                answer = 1;
                break;
            }
        }
        System.out.println(answer);

    }

}