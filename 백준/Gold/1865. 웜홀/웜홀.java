import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dist;

    // 벨만 포드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestcases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTestcases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numberOfNode = Integer.parseInt(st.nextToken());
            int numberOfRoad = Integer.parseInt(st.nextToken());
            int numberOfWarmHole = Integer.parseInt(st.nextToken());

            dist = new int[numberOfNode + 1];
            List<List<Edge2>> graph = new ArrayList<>();
            for (int j = 0; j <= numberOfNode; j++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < numberOfRoad; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Edge2(start, end, time));
                graph.get(end).add(new Edge2(end, start, time));
            }
            for (int j = 0; j < numberOfWarmHole; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Edge2(start, end, time * -1));
            }
            String answer = "NO\n";
            boolean isCycle =false;
            int[] time = new int[numberOfNode + 1];
            Arrays.fill(time, 987654321);
            time[1] = 0;
            boolean isUpdate = false;
            for (int j = 1; j < numberOfNode ; j++) {
                isUpdate = false;

                for (int k = 1; k <= numberOfNode; k++) {
                    for (Edge2 edge : graph.get(k)) {
                        if (time[edge.getEnd()] > time[k] + edge.getTime()){
                            time[edge.getEnd()] = time[k] + edge.getTime();
                            isUpdate = true;
                        }
                    }
                }
                if(!isUpdate){
                    break;
                }
            }
            if(isUpdate){
                for (int k = 1; k <= numberOfNode; k++) {
                    for (Edge2 edge : graph.get(k)) {
                        if (time[edge.getEnd()] > time[k] + edge.getTime()) {
                            isCycle = true;
                            break;
                        }
                    }
                    if(isCycle){
                        break;
                    }
                }
            }
            if(isCycle){
                answer = "YES\n";
            }
            sb.append(answer);
        }
        System.out.println(sb);

        br.close();
    }
}

class Edge2 {
    private final int start;
    private final int end;
    private final int time;

    public Edge2(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}