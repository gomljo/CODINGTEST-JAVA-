import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfPeople = Integer.parseInt(st.nextToken());
        int numberOfParty = Integer.parseInt(st.nextToken());
        boolean[] truthPeople = new boolean[51];
        st = new StringTokenizer(br.readLine());
        int numberOfKnowingTruth = Integer.parseInt(st.nextToken());

        if (numberOfKnowingTruth > 0) {
            for (int i = 0; i < numberOfKnowingTruth; i++) {
                truthPeople[Integer.parseInt(st.nextToken())] = true;
            }
        }
        int answer = 0;
        Union union = new Union(numberOfPeople);
        union.initializeParent();
        List<List<Integer>> party = new ArrayList<>();
        for (int i = 0; i < numberOfParty; i++) {
            st = new StringTokenizer(br.readLine());
            int numberOfParticipants = Integer.parseInt(st.nextToken());
            List<Integer> people = new ArrayList<>();
            for (int j = 0; j < numberOfParticipants; j++) {
                int person = Integer.parseInt(st.nextToken());
                people.add(person);
                if (people.size() > 1) {
                    union.union(people.get(j), people.get(j - 1));
                }
            }
            party.add(people);
        }
        for (int i = 1; i < truthPeople.length; i++) {
            if(truthPeople[i]){
                truthPeople[union.find(i)] = true;
            }
        }
        int parent;
        for (List<Integer> people : party) {
            if (people.size() > 0) {
                parent = union.find(people.get(0));
                if(!truthPeople[parent]){
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }
}

class Union {
    private final int[] parent;

    public Union(int numberOfPeople) {
        this.parent = new int[numberOfPeople + 1];
    }

    public void initializeParent() {
        for (int i = 1; i < this.parent.length; i++) {
            this.parent[i] = i;
        }
    }

    public void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (a != b) {
            this.parent[bParent] = aParent;
        }
    }

    public int find(int person) {
        if (this.parent[person] != person) {
            this.parent[person] = find(this.parent[person]);
        }
        return this.parent[person];
    }

    public void printParent() {
        System.out.println(Arrays.toString(this.parent));
    }
}