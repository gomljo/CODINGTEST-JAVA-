import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sequence = scanner.next();
        int numberOfA = (int) Arrays.stream(sequence.split("")).filter(element -> element.equals("a")).count();
        int answer = Integer.MAX_VALUE;
        String doubledSequence = sequence + sequence;
        int start = 0;
        while (start < sequence.length()) {
            String window = doubledSequence.substring(start, start + numberOfA);
            int numberOfB = (int) Arrays.stream(window.split("")).filter(element -> element.equals("b")).count();
            answer = Math.min(answer, numberOfB);
            start++;
        }
        System.out.println(answer);
        scanner.close();
    }
}