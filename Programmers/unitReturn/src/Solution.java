import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        // 다익스트라 알고리즘
        // destination을 기점으로 거리를 계산한다.
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n+1; i++) {
            graph.
        }

        // graph 생성
        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            if (!graph.containsKey(start)) {
                List<Integer> nodes = new ArrayList<>();
                nodes.add(end);
                graph.put(start, nodes);
            } else {
                graph.get(start).add(end);
            }

            if (!graph.containsKey(end)) {
                List<Integer> nodes = new ArrayList<>();
                nodes.add(start);
                graph.put(end, nodes);
            } else {
                graph.get(end).add(start);
            }
        }
        // graph 확인
        System.out.println(graph);

        // graph 순회
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);

        while (!queue.isEmpty()){
            int start = queue.poll();


        }

        return answer;
    }
}

class Main{
    public static void main(String[] args) {
        int n = 3;
        int[][] roads = {{1, 2}, {2, 3}};
        int[] sources = {2,3};
        int destination = 1;
        Solution s = new Solution();
        s.solution(n, roads, sources, destination);
    }
}