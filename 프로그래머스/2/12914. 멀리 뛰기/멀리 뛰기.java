class Solution {
    public long solution(int n) {
        if(n < 3) {
            return n;
        }
        long[] jump = new long[3];
        jump[0] = 1;
        jump[1] = 2;
        int trial = 3;
        while (trial <= n){
            jump[2] = (jump[1] + jump[0])%1234567;
            jump[0] = jump[1];
            jump[1] = jump[2];
            trial++;
        }

        return jump[2];
    }
}
