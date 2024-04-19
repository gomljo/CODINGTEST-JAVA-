import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> expression = Arrays.stream(scanner.next().split("")).collect(Collectors.toList());
        PipeCutter pipeCutter = new PipeCutter(expression);
        pipeCutter.cut();
        System.out.println(pipeCutter.getNumberOfPieces());

        scanner.close();
    }
}

class PipeCutter {
    private static final String PIPE_START = "(";
    private static final String PIPE_END = ")";
    private static final int LASER_INTERVAL = 1;
    private static final int ONE_PIECE = 1;
    private final List<String> pipeExpression;
    private int numberOfPieces;

    public PipeCutter(List<String> pipeExpression) {
        this.pipeExpression = pipeExpression;
        this.numberOfPieces = 0;
    }

    public void cut() {
        Stack<Pipe> pipeStack = new Stack<>();

        for (int i = 0; i < this.pipeExpression.size(); i++) {
            String value = this.pipeExpression.get(i);
            if (!pipeStack.isEmpty() && value.equals(PIPE_END)) {
                Pipe top = pipeStack.pop();
                if (this.isLaser(i, top)) {
                    this.numberOfPieces += pipeStack.size();
                } else {
                    this.numberOfPieces += ONE_PIECE;
                }
            }
            else if(value.equals(PIPE_START)){
                pipeStack.push(new Pipe(i, value));
            }
        }
    }
    public int getNumberOfPieces(){
        return this.numberOfPieces;
    }

    private boolean isLaser(int currentIndex, Pipe top) {
        return Math.abs(currentIndex - top.getStart()) == LASER_INTERVAL;
    }
}

class Pipe {
    private final int start;
    private final String value;

    public Pipe(int start, String value) {
        this.start = start;
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "start=" + start +
                ", value='" + value + '\'' +
                '}';
    }
}