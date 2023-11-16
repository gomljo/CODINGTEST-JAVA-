class Solution {
    public static boolean[] crossFromLeft;
    public static boolean[] crossFromRight;
    public static boolean[] flat;
    public static int cases=0;
    public static void setQueen(int position, int n){

        for (int i = 0; i < n; i++) {
            if(!flat[i] && !crossFromRight[position + i] && !crossFromLeft[position - i + n - 1]){
                
                if(position==n-1){
                    cases++;
                    return;
                }
                else {
                    flat[i] = crossFromRight[position + i] = crossFromLeft[position - i + n - 1] = true;
                    setQueen(position+1, n);
                    flat[i] = crossFromRight[position + i] = crossFromLeft[position - i + n - 1] = false;
                }
            }
        }
    }

    public int solution(int n) {
        int answer = 0;
        crossFromLeft = new boolean[n*2 -1];
        crossFromRight = new boolean[n*2 -1];
        flat = new boolean[n];

        setQueen(0, n);
        return cases;
    }
}
