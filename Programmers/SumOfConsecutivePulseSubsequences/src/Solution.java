import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        int purseType1 = 1;
        int purseType2 = -1;
        long[] dp1 = new long[sequence.length+1];
        long[] dp2 = new long[sequence.length+1];

        for(int i=1; i<=sequence.length; i++){

            long num = sequence[i-1];
            long num1 = num * purseType1;
            long num2 = num * purseType2;
            dp1[i] = Math.max(dp1[i-1] + num1, num1);
            dp2[i] = Math.max(dp2[i-1] + num2, num2);

            purseType1 *= -1;
            purseType2 *= -1;
        }
        long dp1Max = Arrays.stream(dp1).max().getAsLong();
        long dp2Max = Arrays.stream(dp2).max().getAsLong();

        return Math.max(dp1Max, dp2Max);
    }
}


class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        long answer = solution.solution(sequence);
        System.out.println(answer);
    }
}