import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfPeople = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int person = Integer.parseInt(st.nextToken());
        int otherPerson = Integer.parseInt(st.nextToken());
        int numberOfRelations = Integer.parseInt(br.readLine());
        List<Node> relations = new ArrayList<>();

        for (int i = 0; i <= numberOfPeople; i++) {
            relations.add(new Node(i));
        }
        for (int i = 0; i < numberOfRelations; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            relations.get(child).setParent(parent);
        }
        RelationFinder finder = new RelationFinder(relations);
        finder.find(person, otherPerson);
        finder.printResult();

        br.close();
    }
}

class RelationFinder {
    private final List<Node> familyTree;
    private int howFar;

    public RelationFinder(List<Node> familyTree) {
        this.familyTree = familyTree;
        this.howFar = -1;
    }

    public void find(int person, int otherPerson) {
        Node node = familyTree.get(person);
        Node otherNode = familyTree.get(otherPerson);
        List<Integer> parentOfPerson = findParent(node.getIndex(), new ArrayList<>());
        List<Integer> parentOfOtherPerson = findParent(otherNode.getIndex(), new ArrayList<>());
        boolean isFind = false;
        for (int i = 0; i < parentOfPerson.size(); i++) {
            for (int j = 0; j < parentOfOtherPerson.size(); j++) {
                if (Objects.equals(parentOfOtherPerson.get(j), parentOfPerson.get(i))) {
                    this.howFar = i + j;
                    isFind = true;
                }
            }
            if (isFind) {
                break;
            }
        }
    }

    private List<Integer> findParent(int index, List<Integer> parents) {
        Node node = this.familyTree.get(index);
        if (node.isRootParent()) {
            parents.add(index);
            return parents;
        }
        parents.add(node.getIndex());
        return findParent(node.getParent(), parents);
    }

    public void printResult() {
        System.out.println(this.howFar);
    }
}

class Node {
    private int parent;

    public int getIndex() {
        return index;
    }

    private final int index;

    public Node(int index) {
        this.parent = -1;
        this.index = index;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getParent() {
        return parent;
    }

    public boolean isRootParent() {
        return this.parent == -1;
    }

    public boolean equals(Node node) {
        return node.getIndex() == this.index;
    }

    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                ", index=" + index +
                '}';
    }
}