import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static boolean isUnCrossed(Line current, Line next){
        return current.getStart() < next.getStart() && current.getEnd() < next.getEnd();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }

        lines.sort(Comparator.comparing(Line::getStart));

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            Line current = lines.get(i);
            for (int j = 0; j < i; j++) {
                Line next = lines.get(j);
                if(current.getEnd() > next.getEnd()){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(n - max);
    }
}

class Line {
    private final int start;
    private final int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}