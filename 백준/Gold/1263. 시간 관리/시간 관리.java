import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] tasks = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int duration = Integer.parseInt(st.nextToken());
            int deadLine = Integer.parseInt(st.nextToken());
            tasks[i][0] = duration;
            tasks[i][1] = deadLine;
        }
        Arrays.sort(tasks, (task1, task2) -> task2[1] - task1[1]);

        int endTime = tasks[0][1] - tasks[0][0];

        for (int i = 1; i < tasks.length; i++) {
            if(tasks[i][1] < endTime) {
                endTime = tasks[i][1];
            }
            endTime = endTime - tasks[i][0];
        }

        if(endTime > 0){
            System.out.println(endTime);
        }
        else {
            System.out.println(-1);
        }
        
    }
}