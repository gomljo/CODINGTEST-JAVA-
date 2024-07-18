import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        FieldInformation fieldInformation = InputReader.read();
        Surveyor surveyor = new Surveyor(fieldInformation);
        surveyor.measureEachCost();
        long sum = surveyor.examine();
        System.out.println(sum);
    }
}


class InputReader {
    public static FieldInformation read() {
        List<Field> fieldList = new ArrayList<>();
        StringTokenizer stringTokenizer;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int numberOfFields = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < numberOfFields; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(stringTokenizer.nextToken());
                int col = Integer.parseInt(stringTokenizer.nextToken());
                fieldList.add(new Field(row, col));
            }
            return new FieldInformation(fieldList, numberOfFields, cost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Field {
    private final int row;
    private final int col;

    public Field(int row, int col) {
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

class FieldInformation {
    private final List<Field> fieldList;
    private final int numberOfField;
    private final int minimumCost;

    public FieldInformation(List<Field> fieldList, int numberOfField, int minimumCost) {
        this.fieldList = fieldList;
        this.numberOfField = numberOfField;
        this.minimumCost = minimumCost;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public int getNumberOfField() {
        return numberOfField;
    }

    public int getMinimumCost() {
        return minimumCost;
    }
}

class Node {
    private final int start;
    private final int end;
    private final long distance;

    public Node(int start, int end, long distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public long getDistance() {
        return distance;
    }
}

class Surveyor {
    private final FieldInformation fieldInformation;
    private final PriorityQueue<Node> priorityQueue;
    private final int[] parent;

    public Surveyor(FieldInformation fieldInformation) {
        this.fieldInformation = fieldInformation;
        this.priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));
        this.parent = new int[this.fieldInformation.getNumberOfField() + 1];
    }

    public void measureEachCost() {
        List<Field> fieldList = this.fieldInformation.getFieldList();
        for (int i = 1; i <= fieldList.size(); i++) {
            parent[i] = i;
            for (int j = 1; j <= fieldList.size(); j++) {
                if (i == j) {
                    continue;
                }
                Field field = fieldList.get(i - 1);
                Field otherField = fieldList.get(j - 1);
                long cost = (long) Math.pow(field.getRow() - otherField.getRow(), 2) + (long) Math.pow(field.getCol() - otherField.getCol(), 2);
                if (cost >= fieldInformation.getMinimumCost()) {
                    priorityQueue.offer(new Node(i, j, cost));
                }
            }
        }
    }

//    public long examine() {
//        // 다익스트라로 시도했지만 다익스트라 알고리즘은 사이클 생성 문제를 해결할 수 없다.
//
//        boolean[] visited = new boolean[this.fieldInformation.getNumberOfField() + 1];
//        long[] minimumCost = new long[this.fieldInformation.getNumberOfField() + 1];
//        Arrays.fill(minimumCost, 2000000);
//        minimumCost[1] = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(1);
//        visited[1] = true;
//        while (!queue.isEmpty()) {
//            Integer index = queue.poll();
//            for (int i = 1; i <= this.fieldInformation.getNumberOfField(); i++) {
//                if (!visited[i]
//                        && this.costs[index][i] >= this.fieldInformation.getMinimumCost()
//                        && minimumCost[index] + this.costs[index][i] < minimumCost[i]) {
//                    visited[i] = true;
//                    minimumCost[i] = minimumCost[index] + this.costs[index][i];
//                    queue.offer(i);
//                }
//            }
//        }
//        return calculateSum(minimumCost);
//    }

    public long examine() {
        // 크루스칼 알고리즘
        // 최소 신장 트리를 만드는 문제였다.
        int count = 0;
        long cost = 0;
        while (!this.priorityQueue.isEmpty()) {
            Node node = this.priorityQueue.poll();

            int start = find(node.getStart());
            int end = find(node.getEnd());
            if (start != end) {
                union(start, end);
                count++;
                cost += node.getDistance();
                if (count == this.fieldInformation.getNumberOfField() - 1) {
                    break;
                }
            }
        }
        if (count != this.fieldInformation.getNumberOfField() - 1) {
            return -1;
        }
        return cost;
    }

    private int find(int child) {
        if (child == parent[child]) {
            return child;
        }
        return parent[child] = find(parent[child]);
    }

    private void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX < parentY) {
            parent[parentY] = parentX;
        } else {
            parent[parentX] = parentY;
        }
    }

    private long calculateSum(long[] minimumCost) {
        long sum = 0;
        for (long cost : minimumCost) {
            if (cost != 2000000) {
                sum += cost;
            }
        }
        return sum;
    }
}