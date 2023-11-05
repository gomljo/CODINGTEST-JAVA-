class Solution {


    public static int getOnes(int n){
        int numberOfOnes = 0;
        while (n != 0){
            if(n%2==1){
                numberOfOnes+=1;
            }
            n /= 2;
        }
        return numberOfOnes;
    }

    public int solution(int n) {
        int answer = 0;
        int target = getOnes(n);
        int next = n;
        while (true){
            next+=1;
            int compare = getOnes(next);
            if(compare==target){
                answer = next;
                break;
            }
        }

        return answer;
    }
}
