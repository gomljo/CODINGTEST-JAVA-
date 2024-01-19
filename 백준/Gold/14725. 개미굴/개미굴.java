import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void makeFoodMap(Node current, String[] food, int index) {
        if (index >= food.length) {
            return;
        }
        Node next;
        if (!current.isFoodOnThisLevel(food[index])) {
            // 타고 들어가야함
            current.addNode(food[index]);
        }
        next = current.findNode(food[index]);
        makeFoodMap(next, food, index + 1);
    }

    public static void printFoodMap(Node root) {
        List<Node> children = root.getNextLevelNodes();
        children.sort(Comparator.comparing(Node::getFood));
        for (Node node: children) {
            String levelExpression = "-".repeat(node.getLevel()*2);
            System.out.println(levelExpression+node.getFood());
            printFoodMap(node);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfInformation = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Node root = new Node("root", -1);
        Node cur;
        for (int i = 0; i < numberOfInformation; i++) {
            cur = root;
            st = new StringTokenizer(br.readLine());
            int numberOfDepth = Integer.parseInt(st.nextToken());
            String[] foods = new String[numberOfDepth];
            for (int j = 0; j < numberOfDepth; j++) {
                foods[j] = st.nextToken();
            }
            makeFoodMap(cur, foods, 0);
        }
        printFoodMap(root);
    }
}

class Node {
    private final List<Node> nextLevelNodes;
    private final String food;
    private final int level;

    public Node(String food, int level) {
        this.nextLevelNodes = new ArrayList<>();
        this.food = food;
        this.level = level;
    }

    public List<Node> getNextLevelNodes() {
        return nextLevelNodes;
    }

    public String getFood() {
        return food;
    }

    public int getLevel() {
        return level;
    }

    public boolean isFoodOnThisLevel(String food) {
        for (Node node : this.nextLevelNodes) {
            if (node.food.equals(food)) {
                return true;
            }
        }
        return false;
    }

    public Node addNode(String food) {
        Node node = new Node(food, this.level + 1);
        this.nextLevelNodes.add(node);
        return node;
    }

    public Node findNode(String food) {
        for (Node node : this.nextLevelNodes) {
            if (node.food.equals(food)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nextLevelNodes=" + nextLevelNodes +
                ", food='" + food + '\'' +
                ", level=" + level +
                '}';
    }
}