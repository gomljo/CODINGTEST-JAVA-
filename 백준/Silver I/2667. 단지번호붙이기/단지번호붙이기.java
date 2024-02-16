import java.util.*;

public class Main {
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[][] MOVES = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        // 단지의 수를 구한다.
        // 단지의 크기를 구한다.
        // BFS 방식으로 풀 수 있을 것 같다.
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        map = new int[size][size];

        // 방문 배열이 필요해 보인다.
        visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            String[] columnElement = sc.next().split("");
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(columnElement[j]);
                if (map[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        int numberOfComplex = 0;
        List<Integer> complexSizeList = new ArrayList<>();
        // 단지의 크기를 반환하도록하고 만약 단지의 크기가 0보다 크다면 단지의 갯수를 증가시킨다.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int complexSize = findComplex(i, j);
                    if (complexSize > 0) {
                        numberOfComplex++;
                        complexSizeList.add(complexSize);
                    }
                }
            }
        }
        System.out.println(numberOfComplex);
        complexSizeList.sort(Comparator.naturalOrder());
        for (Integer complexSize : complexSizeList) {
            System.out.println(complexSize);
        }
    }

    public static int findComplex(int row, int col) {
        Queue<Finder> queue = new LinkedList<>();
        int totalComplexSize = 0;
        if (visited[row][col]) {
            return totalComplexSize;
        }
        queue.add(new Finder(row, col, 1));
        while (!queue.isEmpty()) {
            Finder poll = queue.poll();
            if(visited[poll.getRow()][poll.getColumn()]){
                continue;
            }
            totalComplexSize++;
            visited[poll.getRow()][poll.getColumn()] = true;
            for (int[] move : MOVES) {
                int nextRow = poll.getRow() + move[0];
                int nextCol = poll.getColumn() + move[1];
                if (isInTheMap(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                    queue.add(new Finder(nextRow, nextCol, totalComplexSize + 1));
                }
            }
        }
        return totalComplexSize;
    }

    public static boolean isInTheMap(int nextRow, int nextCol) {
        return (0 <= nextRow && nextRow < map.length)
                && (0 <= nextCol && nextCol < map[0].length);
    }
}

class Finder {
    private int row;
    private int column;
    private int size;

    public Finder(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Finder{" +
                "row=" + row +
                ", column=" + column +
                ", size=" + size +
                '}';
    }
}