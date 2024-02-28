import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 1. 0이 아닌 곳까지의 경로의 길이 중 가장 긴 경로를 가지는 경로들을 구한다.
    // 2. 그 중 시작 점과 끝 점이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        int[][] map = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        PathFinder pathFinder = new PathFinder(map);
        int password = pathFinder.findPassword();
        System.out.println(password);
    }


}

class PathFinder {
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final int[][] map;
    private final int rowSize;
    private final int columnSize;

    public PathFinder(int[][] map) {
        this.map = map;
        this.columnSize = map[0].length;
        this.rowSize = map.length;
    }

    public int findPassword() {
        Password optimalPassword = new Password();
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                if (map[row][column] == 0) {
                    continue;
                }

                Password password = breadthFirstSearch(row, column, map[row][column]);
                password.updateStart(map[row][column]);
                if (optimalPassword.isCurrentPathLengthShorterThan(password.getPathLength())) {
                    optimalPassword.updateStart(password.getStart());
                    optimalPassword.updateEnd(password.getEnd());
                    optimalPassword.updatePathLength(password.getPathLength());
                }

                if (optimalPassword.isCurrentPathLengthEqual(password.getPathLength())) {
                    if (optimalPassword.isCurrentPasswordSmallerThan(password.getPassword())) {
                        optimalPassword.updateStart(password.getStart());
                        optimalPassword.updateEnd(password.getEnd());
                    }
                }
            }
        }
        return optimalPassword.getPassword();
    }

    private Password breadthFirstSearch(int row, int column, int number) {
        Password password = new Password();
        Queue<Room> roomQueue = new LinkedList<>();
        roomQueue.add(new Room(row, column, number, 1));
        boolean[][] visited = new boolean[this.rowSize][this.columnSize];
        while (!roomQueue.isEmpty()) {
            Room current = roomQueue.poll();
            if (password.isCurrentPathLengthShorterThan(current.getPathLength())) {
                password.updateEnd(current.getNumber());
                password.updatePathLength(current.getPathLength());
            }

            if (password.isCurrentPathLengthEqual(current.getPathLength())) {
                if (password.isCurrentEndSmallerThan(current.getNumber())) {
                    password.updateEnd(current.getNumber());
                }
            }

            if (visited[current.getRow()][current.getColumn()]) {
                continue;
            }
            visited[current.getRow()][current.getColumn()] = true;

            for (int[] move : MOVES) {
                int nextRow = current.getRow() + move[0];
                int nextColumn = current.getColumn() + move[1];
                if (isInMap(nextRow, nextColumn)
                        && !visited[nextRow][nextColumn]
                        && map[nextRow][nextColumn] != 0) {
                    roomQueue.add(new Room(nextRow, nextColumn, map[nextRow][nextColumn], current.getPathLength()+1));
                }
            }
        }

        return password;
    }

    private boolean isInMap(int nextRow, int nextColumn) {
        return (0 <= nextRow && nextRow < rowSize)
                && (0 <= nextColumn && nextColumn < columnSize);
    }

}

class Password {
    private static final int INITIAL_START = 0;
    private static final int INITIAL_END = 0;
    private static final int INITIAL_PATH_LENGTH = 0;
    private int start;
    private int end;
    private int pathLength;

    public Password() {
        this.start = INITIAL_START;
        this.end = INITIAL_END;
        this.pathLength = INITIAL_PATH_LENGTH;
    }

    public int getEnd() {
        return this.end;
    }

    public int getStart() {
        return this.start;
    }
    public void updateStart(int newStart){
        this.start = newStart;
    }
    public int getPassword() {
        return this.start + this.end;
    }

    public int getPathLength() {
        return pathLength;
    }
    public boolean isCurrentPasswordSmallerThan(int newPassword){
        return (this.start + this.end) < newPassword;
    }
    public boolean isCurrentEndSmallerThan(int newEnd) {
        return this.end < newEnd;
    }

    public void updateEnd(int newEnd) {
        this.end = newEnd;
    }

    public boolean isCurrentPathLengthShorterThan(int newPathLength) {
        return this.pathLength < newPathLength;
    }

    public boolean isCurrentPathLengthEqual(int newPathLength) {
        return this.pathLength == newPathLength;
    }

    public void updatePathLength(int newPathLength) {
        this.pathLength = newPathLength;
    }

    @Override
    public String toString() {
        return "Password{" +
                "start=" + start +
                ", end=" + end +
                ", pathLength=" + pathLength +
                '}';
    }
}

class Room {
    private final int row;
    private final int column;
    private final int number;
    private final int pathLength;

    public Room(int row, int column, int number, int pathLength) {
        this.row = row;
        this.column = column;
        this.number = number;
        this.pathLength = pathLength;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }

    public int getPathLength() {
        return pathLength;
    }
}