import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int NUMBER_OF_RECTANGLES = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RectangleAreaCalculator rectangleAreaCalculator = new RectangleAreaCalculator();
        for (int i = 0; i < NUMBER_OF_RECTANGLES; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int leftDownRow = Integer.parseInt(st.nextToken());
            int leftDownCol = Integer.parseInt(st.nextToken());
            int rightUpRow = Integer.parseInt(st.nextToken());
            int rightUpCol = Integer.parseInt(st.nextToken());

            for (int j = leftDownRow; j < rightUpRow; j++) {
                for (int k = leftDownCol; k < rightUpCol; k++) {
                    Coordinate leftDown = new Coordinate(j, k);
                    Coordinate rightUp = new Coordinate(j+1, k+1);
                    Rectangle rectangle = new Rectangle(leftDown, rightUp);
                    if(!rectangleAreaCalculator.isAlreadyContain(rectangle)){
                        rectangleAreaCalculator.addRectangle(rectangle);
                    }
                }
            }
        }
        System.out.println(rectangleAreaCalculator.getTotalArea());

    }
}

class RectangleAreaCalculator {
    private final List<Rectangle> rectangles;

    public RectangleAreaCalculator() {
        this.rectangles = new ArrayList<>();
    }

    public void addRectangle(Rectangle rectangle){
        this.rectangles.add(rectangle);
    }

    public boolean isAlreadyContain(Rectangle rectangle){
        return this.rectangles.contains(rectangle);
    }

    public int getTotalArea(){
        return this.rectangles.size();
    }

}

class Rectangle {
    private final Coordinate leftDown;
    private final Coordinate rightDown;

    public Rectangle(Coordinate leftDown, Coordinate rightDown) {
        this.leftDown = leftDown;
        this.rightDown = rightDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Rectangle otherRectangle = (Rectangle) o;
        return this.leftDown.getRow() == otherRectangle.getLeftDown().getRow()
                && this.leftDown.getCol() == otherRectangle.getLeftDown().getCol()
                && this.rightDown.getRow() == otherRectangle.getRightDown().getRow()
                && this.rightDown.getCol() == otherRectangle.getRightDown().getCol();
    }

    public Coordinate getLeftDown() {
        return leftDown;
    }

    public Coordinate getRightDown() {
        return rightDown;
    }
}

class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}