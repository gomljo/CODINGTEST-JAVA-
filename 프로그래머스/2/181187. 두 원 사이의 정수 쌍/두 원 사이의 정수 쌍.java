class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long inner;
        long outer;
        long r2Square = (long)Math.pow(r2, 2);
        long r1Square = (long)Math.pow(r1, 2);
        int border = 0;
        for (long i = 0; i <= r2; i++) {

            inner = (long)(Math.sqrt(r1Square - i*i));
            outer = (long)(Math.sqrt(r2Square - i*i));
            if(Math.sqrt(r1Square - i*i) % 1L == 0L) {
                border++;
            }

            answer += (outer - inner)*4;
        }
        answer += border*4L;
        return answer - 4;
    }
}