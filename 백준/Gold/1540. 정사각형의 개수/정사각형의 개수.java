import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPoint = scanner.nextInt();
        int squareRoot = (int) Math.sqrt(numberOfPoint);
        int diff = numberOfPoint - squareRoot*squareRoot;
        int answer = 0;
        for (int i = 1; i < squareRoot; i++) {
            answer += i * i;
        }
        int adding = 0;
        for (int i = diff; i >0; i--) {
            answer += adding;
            adding = (adding+1) % squareRoot;
        }
        System.out.println(answer);
        scanner.close();

    }
}