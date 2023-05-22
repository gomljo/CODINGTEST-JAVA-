import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        int answer = 0;
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] target1, int[] target2) {
                return target1[0] != target2[0] ? target1[0] - target2[0] : target1[1] - target2[1];
            }
        });
//        System.out.println(Arrays.deepToString(targets));
        int idx = 0;
        while (idx < targets.length){
//            System.out.println("idx = " + idx);
            int[] target = targets[idx++];
//            System.out.println(Arrays.toString(target));
            int start = target[0];
            int end = target[1];
            int numOfDeleted = 0;
            for (int i = idx; i < targets.length; i++) {
                if(targets[i][1] <= start){
                   continue;
                }
                if(targets[i][0] >= end){
                    continue;
                }
//                System.out.println("targets[" + i+"] = " + Arrays.toString(targets[i]));
                numOfDeleted++;
            }
//            System.out.println(numOfDeleted);
            idx += numOfDeleted;
            answer += 1;
        }
        System.out.println(answer);
    }


}
