import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        // 컨테이너 벨트는 한 방향으로만 진행이 가능해서 벨트에 놓인 순서대로(1번 상자부터) 상자를 내릴 수 있습니다.
        // 보조 컨테이너 벨트는 앞 뒤로 이동이 가능하지만 입구 외에 다른 면이 막혀 있어서 맨 앞의 상자만 뺄 수 있습니다
        // (즉, 가장 마지막에 보조 컨테이너 벨트에 보관한 상자부터 꺼내게 됩니다).
        // 보조 컨테이너 벨트를 이용해도 기사님이 원하는 순서대로 상자를 싣지 못 한다면, 더 이상 상자를 싣지 않습니다.
        int idx = 1;
        Stack<Integer> subBelt = new Stack<>();
        while (idx < order.length + 1) {
            subBelt.push(idx);
            while (subBelt.peek() == order[answer]) {
                answer += 1;
                subBelt.pop();

                if (subBelt.isEmpty()) {
                    break;
                }
            }
            idx++;
        }

        return answer;
    }
}