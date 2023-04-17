import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<int[]> scheduler = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        scheduler.offer(jobs[0]);
        int end = 0;
        int idx = 1;

        while (!scheduler.isEmpty()){
            int[] job = scheduler.poll();
            end += job[1];
            answer += end - job[0];

            while (idx < jobs.length && jobs[idx][0] <= end){
                scheduler.offer(jobs[idx++]);
            }

            if(idx < jobs.length && scheduler.isEmpty()){
                end = jobs[idx][0];
                scheduler.offer(jobs[idx++]);
            }
        }

        return answer / jobs.length;
    }
}