import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int minDistance = Integer.MAX_VALUE;
    static int maxChickenStore;

    // 치킨집 리스트
    static List<int[]> chickenStores = new ArrayList<>();
    static List<int[]> houseList = new ArrayList<>();
    static boolean[] visited;

    public static void simulate(int numberOfChickenStore, int start) {

        if (numberOfChickenStore == maxChickenStore) {
            int currentDistanceSum = 0;
            for (int[] house : houseList) {
                int currentMinDistance = Integer.MAX_VALUE;
                for (int i = 0; i < chickenStores.size(); i++) {
                    if (visited[i]) {
                        int[] chickenStore = chickenStores.get(i);
                        int currentDistance = Math.abs(house[0] - chickenStore[0]) + Math.abs(house[1] - chickenStore[1]);
                        currentMinDistance = Math.min(currentMinDistance, currentDistance);
                    }
                }
                currentDistanceSum += currentMinDistance;
            }
            minDistance = Math.min(minDistance, currentDistanceSum);
            return;
        }

        for (int i = start; i < chickenStores.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                simulate(numberOfChickenStore + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int mapSize = scanner.nextInt();
        maxChickenStore = scanner.nextInt();

        for (int i = 0; i < mapSize; i++) {

            for (int j = 0; j < mapSize; j++) {
                int status = scanner.nextInt();
                // 만약 치킨집이라면 치킨집 리스트에 추가
                if (status == 2) {
                    chickenStores.add(new int[]{i, j});
                }
                else if (status == 1) {
                    houseList.add(new int[]{i, j});
                }
            }
        }
        visited = new boolean[chickenStores.size()];
        // dfs를 활용하여 백트래킹을 진행
        // depth 증가 시 치킨 거리의 총합이 증가한다면 재귀 종료
        simulate(0, 0);
        System.out.println(minDistance);
    }
}
