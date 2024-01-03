import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .mapToInt(l -> l)
                    .toArray();
            lines[i] = new Line(line[0], line[1]);
        }

        Arrays.sort(lines);
        int start = lines[0].getStart();
        int end = lines[0].getEnd();
        int answer = 0;
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].getStart() <= end && end <= lines[i].getEnd()) {
                end = Math.max(end,lines[i].getEnd());
            } else if (end < lines[i].getStart()) {
                answer += end - start;
                start = lines[i].getStart();
                end = lines[i].getEnd();
            }
        }
        answer += end - start;
        System.out.println(answer);
    }
}

class Line implements Comparable<Line> {
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
    public int compareTo(Line o) {
        if (o.start == this.start) {
            return this.end - o.end;
        }
        return this.start - o.start;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}