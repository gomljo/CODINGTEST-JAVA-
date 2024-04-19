import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfPoint = Integer.parseInt(br.readLine());
        List<Coordinate> coordinateList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < numberOfPoint; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            coordinateList.add(new Coordinate(x, y));
        }
        AreaCalculator areaCalculator = new AreaCalculator(coordinateList);
        areaCalculator.calculate();
        System.out.printf("%.1f", areaCalculator.getArea());

        br.close();
    }
}

class AreaCalculator {
    private final List<Coordinate> coordinateList;

    private long sum;

    public AreaCalculator(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        this.sum = 0;
    }

    public void calculate() {
        long firstTerm = 0;
        long secondTerm = 0;
        for (int i = 0; i < coordinateList.size() - 1; i++) {
            Coordinate a = coordinateList.get(i);
            Coordinate b = coordinateList.get(i + 1);
            firstTerm += a.getX() * b.getY();
            secondTerm += b.getX() * a.getY();
        }
        firstTerm += coordinateList.get(coordinateList.size() - 1).getX() * coordinateList.get(0).getY();
        secondTerm += coordinateList.get(0).getX() * coordinateList.get(coordinateList.size() - 1).getY();
        this.sum = Math.abs(firstTerm - secondTerm);
    }

    public double getArea() {
        return Math.round((double) this.sum * 0.5 * 100) / 100.0;
    }
}

class Coordinate {
    private final long x;
    private final long y;

    public Coordinate(long x, long y) {
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