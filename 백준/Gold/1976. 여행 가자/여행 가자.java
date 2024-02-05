import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // 문제에 주어진 예제를 확인해보면
    // 주어진 여행 계획대로 움직이는 것이 가능한 경우도 존재하고 포함되는 경우도 존재한다.
    // 즉, 주어진 여행 계획에 포함된 도시들끼리의 연결 여부를 확인해야 하는 문제이다.
    // 도시들 간의 연결 여부는 프로그래밍 차원에서 노드의 연결 여부를 확인해야 한다.
    // 그렇다면 연결 여부를 어떻게 알 수 있을까?
    // 문제 유형에 나타나 있는 분리 집합(disjoint set)을 이용해야 한다.
    // 분리 집합은 union-find 알고리즘으로 구할 수 있다.
    // 

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
    // 연결된 노드들의 부모 노드를 합침
    public static void union(int a, int b, int[] parent) {
        int aParent = find(a, parent);
        int bParent = find(b, parent);
        if(aParent != bParent){
            parent[aParent] = bParent;
        }
    }

    public static int find(int x, int[] parent) {
        if (x != parent[x]) {
            // 경로 압축
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}