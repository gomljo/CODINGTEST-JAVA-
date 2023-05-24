import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = {};
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        LinkedList<Integer> deque = new LinkedList<>(list);
        int num;
        if(direction.equals("left")){
            num = deque.pollFirst();
            deque.offerLast(num);
        }
        else {
            num = deque.pollLast();
            deque.offerFirst(num);
        }

        return deque.stream().mapToInt(i->i).toArray();
    }
}