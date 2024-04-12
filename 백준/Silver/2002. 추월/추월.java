import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> entrance = new LinkedList<>();
        Queue<String> exit = new LinkedList<>();
        int numberOfCar = scanner.nextInt();

        for (int i = 0; i < numberOfCar; i++) {
            entrance.offer(scanner.next());
        }

        for (int i = 0; i < numberOfCar; i++) {
            exit.offer(scanner.next());
        }
        int numberOfPassingCar = 0;
        while (!exit.isEmpty()) {
            String carNumber = exit.poll();
            if (!entrance.isEmpty() && !entrance.peek().equals(carNumber)) {
                numberOfPassingCar++;
                entrance.remove(carNumber);
                continue;
            }
            entrance.poll();
        }
        System.out.println(numberOfPassingCar);

        scanner.close();
    }
}