import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 2;
        StringTokenizer st;
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            List<Coordinate> coordinateList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                coordinateList.add(new Coordinate(x, y));
            }
            lineList.add(new Line(coordinateList));
        }
        CrossDiscriminator crossDiscriminator = new CrossDiscriminator(lineList);
        crossDiscriminator.discriminate();
        System.out.println(crossDiscriminator.getFirstResult());
    }
}

class CrossDiscriminator {
    private final static int CROSSED = 1;
    private final static int NOT_CROSSED = 0;
    private final Line line;
    private final Line otherLine;
    private int firstResult;
    private int secondResult;

    public CrossDiscriminator(Line line, Line otherLine) {
        this.line = line;
        this.otherLine = otherLine;
    }

    public CrossDiscriminator(List<Line> lineList) {
        this.line = lineList.get(0);
        this.otherLine = lineList.get(1);
        this.firstResult = 0;
        this.secondResult = 0;
    }

    private long crossProduct(Coordinate otherLineStart, Coordinate otherLineEnd, Coordinate pointOfLine) {
        long firstTerm = otherLineStart.getX() * otherLineEnd.getY() + otherLineEnd.getX() * pointOfLine.getY() + pointOfLine.getX() * otherLineStart.getY();
        long secondTerm = otherLineStart.getY() * otherLineEnd.getX() + otherLineEnd.getY() * pointOfLine.getX() + pointOfLine.getY() * otherLineStart.getX();
        return firstTerm - secondTerm;
    }

    private int decideDirection(long crossProductResult) {
        return Long.compare(crossProductResult, 0);
    }

    public void discriminate() {
        long crossProductResultOnLineStart = this.crossProduct(this.otherLine.getStart(), this.otherLine.getEnd(), this.line.getStart());
        long crossProductResultOnLineEnd = this.crossProduct(this.otherLine.getStart(), this.otherLine.getEnd(), this.line.getEnd());
        long crossProductResultOnOtherLineStart = this.crossProduct(this.line.getStart(), this.line.getEnd(), this.otherLine.getStart());
        long crossProductResultOnOtherLineEnd = this.crossProduct(this.line.getStart(), this.line.getEnd(), this.otherLine.getEnd());

        int directionOnLineStart = this.decideDirection(crossProductResultOnLineStart);
        int directionOnLineEnd = this.decideDirection(crossProductResultOnLineEnd);
        int directionOnOtherLineStart = this.decideDirection(crossProductResultOnOtherLineStart);
        int directionOnOtherLineEnd = this.decideDirection(crossProductResultOnOtherLineEnd);

        this.firstResult = directionOnLineStart * directionOnLineEnd;
        this.secondResult = directionOnOtherLineStart * directionOnOtherLineEnd;
    }

    public int getFirstResult() {
        if (this.firstResult <= 0 && this.secondResult <= 0) {
            return CROSSED;
        }
        return NOT_CROSSED;
    }

}

class Line {
    private final Coordinate start;
    private final Coordinate end;

    public Line(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public Line(List<Coordinate> coordinateList) {
        this.start = coordinateList.get(0);
        this.end = coordinateList.get(1);
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}

class Coordinate {
    private final long x;
    private final long y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}