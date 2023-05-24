import java.util.*;
// 다시 풀어보기
// 비트 마스크 유형
public class Main {
    static final int MAX_BIT_LENGTH=20;
    static final int BINARY_DIVISOR=2;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long sum = 0;
        int[] one_cnt = new int[MAX_BIT_LENGTH];
        int n = scanner.nextInt();
        int idx;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            idx = 0;
            while (num!=0){
                int bit = num % BINARY_DIVISOR;
                num /= BINARY_DIVISOR;
                if(bit==1){
                    one_cnt[idx] += 1;
                }
                idx++;
            }

        }
        for (int i = 0; i < MAX_BIT_LENGTH; i++) {
            int zero_cnt = (n-one_cnt[i]); // 각 자리수에서 0과 1의 합은 n개다. 따라서 n-one_cnt[i]는 0의 갯수를 의미한다.
            sum += (1L << i) * one_cnt[i] * zero_cnt;
        }
        System.out.println(sum);
    }
}
