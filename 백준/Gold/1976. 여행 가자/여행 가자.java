import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfCity = Integer.parseInt(br.readLine());
        int numberOfCityInPlan = Integer.parseInt(br.readLine());
        int[] parent = new int[numberOfCity+1];

        for (int i = 0; i < numberOfCity+1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < numberOfCity; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numberOfCity; j++) {
                int connectedOrNot = Integer.parseInt(st.nextToken());
                if (connectedOrNot == 1) {
                    union(i+1, j+1, parent);
                }
                
            }
        }
        List<Integer> plans = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        boolean isPossible = true;
        for (int i = 1; i < plans.size(); i++) {
            if(find(plans.get(0), parent) !=find(plans.get(i), parent)){
                isPossible = false;
                break;
            }
        }
        if(isPossible){
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    public static void union(int a, int b, int[] parent) {
        int aParent = find(a, parent);
        int bParent = find(b, parent);
        if(aParent != bParent){
            parent[bParent] = aParent;
        }
    }

    public static int find(int x, int[] parent) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}