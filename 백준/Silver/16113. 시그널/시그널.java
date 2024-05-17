import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String signal = scanner.next();
        int length = n / 5;
        int[][] analysis = new int[5][length];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < length; j++) {
                if (signal.charAt(i * length + j) == '#') {
                    analysis[i][j] = 1;
                }
            }
        }
        SignalAnalyzer analyzer = new SignalAnalyzer(analysis);
        analyzer.analyze();


    }
}

class SignalAnalyzer {
    private static final int[][] ONE = new int[][]{{1}, {1}, {1}, {1}, {1}};
    private static final int[][] ZERO = new int[][]{{1,1,1},{1,0,1},{1,0,1},{1,0,1},{1,1,1}};
    private static final int[][] TWO = new int[][]{{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}};
    private static final int[][] THREE = new int[][]{{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};
    private static final int[][] FOUR = new int[][]{{1, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {0, 0, 1}};
    private static final int[][] FIVE = new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};
    private static final int[][] SIX = new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    private static final int[][] SEVEN = new int[][]{{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}};
    private static final int[][] EIGHT = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    private static final int[][] NINE = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};
    private static final int[][][] NUMBERS = new int[][][]{TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};
    private int[][] analysis;
    private String answer;

    public SignalAnalyzer(int[][] analysis) {
        this.analysis = analysis;
    }

    public void analyze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.analysis[0].length; ) {
            if (analysis[0][i] == 1) {
                int start = i;
                while (i < this.analysis[0].length && (analysis[0][i] == 1 || analysis[1][i]==1 || analysis[2][i]==1 || analysis[3][i]==1 || analysis[4][i]==1)) {
                    i++;
                }
                int result = findNumber(i - start, start, i);
                if (result != -1) {
                    sb.append(result);
                }
            }
            i++;
        }
        this.answer = sb.toString();
        System.out.println(answer);
    }

    public int findNumber(int length, int start, int end) {
        if (length == 1) {
            // 1을 확인하고 리턴
            for (int i = 0; i < 5; i++) {
                if (this.analysis[i][start] != 1) {
                    return -1;
                }
            }
            return 1;
        }
        if(isZero(start, end)){
            return 0;
        }
        for (int i = 0; i < NUMBERS.length; i++) {
            if (isMatch(NUMBERS[i], start, end)) {
                return i + 2;
            }
        }



        return -1;
    }

    private boolean isMatch(int[][] number, int start, int end) {
        for (int i = 0; i < 5; i++) {
            for (int j = start; j < end; j++) {
                if (number[i][j - start] != this.analysis[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isZero(int start, int end){
        for (int i = 0; i < 5; i++) {
            for (int j = start; j < end; j++) {
                if (ZERO[i][j - start] != this.analysis[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

//    private boolean isOne(int start){
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            if()
//        }
//    }
}