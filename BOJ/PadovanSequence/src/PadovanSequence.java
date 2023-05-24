import java.util.Arrays;
import java.util.Scanner;

public class PadovanSequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            long[] answer = new long[100];
            answer[0] = 1L;
            answer[1] = 1L;
            answer[2] = 1L;
            int order = scanner.nextInt();
            if(order-1 < 3){
                sb.append(String.format("%d\n", answer[order-1]));
            }
            else {
                for (int j = 2; j < order-1; j++) {
                    answer[j+1] = answer[j-1] + answer[j-2];
                }
                sb.append(String.format("%d\n", answer[order-1]));
            }
        }

        System.out.println(sb);
    }

}
