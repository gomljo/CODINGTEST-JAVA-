import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numberOfCase; i++) {
            String[] command = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new LinkedList<>();
            String expression = br.readLine();
            expression = expression.replaceAll("[\\[\\],]", " ");
            expression = expression.trim();
            String[] numbers = expression.split(" ");
            int direction = 1;
            for (String number : numbers) {
                if (!number.equals("")) {
                    deque.offerLast(Integer.parseInt(number));
                }
            }
            boolean isErrorOccurred = false;
            for (String s : command) {
                if (s.equals("R")) {
                    direction *= -1;
                } else {
                    if(deque.isEmpty()){
                        System.out.println("error");
                        isErrorOccurred = true;
                        break;
                    }
                    if (direction == 1) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }
            if(!isErrorOccurred){
                List<String> result = new ArrayList<>();
                while (!deque.isEmpty()){
                    if(direction==-1){
                        result.add(String.valueOf(deque.pollLast()));
                    }
                    else {
                        result.add(String.valueOf(deque.pollFirst()));
                    }
                }
                String resultForm = "[" + String.join(",", result) + "]";
                System.out.println(resultForm);
            }
        }
    }
}