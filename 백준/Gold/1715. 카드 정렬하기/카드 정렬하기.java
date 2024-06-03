import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            priorityQueue.add(scanner.nextInt());
        }

        while (!priorityQueue.isEmpty()){
            int num = priorityQueue.poll();
            int num2;
            if(!priorityQueue.isEmpty()){
                num2 = priorityQueue.poll();
                priorityQueue.offer(num+num2);
                answer += num+num2;
            }
            else{
                System.out.println(answer);
            }
        }


    }
}