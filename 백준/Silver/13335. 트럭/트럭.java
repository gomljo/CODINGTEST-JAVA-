import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int numberOfTrucks = Integer.parseInt(params[0]);
        int bridgeLength = Integer.parseInt(params[1]);
        int maxWeight = Integer.parseInt(params[2]);
        int[] trucks = new int[numberOfTrucks];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfTrucks; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int currentWeight = 0;

        int time = 0;
        Queue<Integer> queue = Arrays.stream(trucks).boxed().collect(Collectors.toCollection(LinkedList::new));
        Queue<Truck> onBridge = new LinkedList<>();
        while (!queue.isEmpty() || !onBridge.isEmpty()) {
            if (!onBridge.isEmpty() && onBridge.peek().getTime() == time) {
                currentWeight -= onBridge.poll().getWeight();
            }
            if (!queue.isEmpty() && currentWeight + queue.peek() <= maxWeight) {
                int weight = queue.poll();
                currentWeight += weight;
                onBridge.add(new Truck(time + bridgeLength, weight));
            }
            time++;
        }
        System.out.println(time);
    }

    static class Truck {
        private final int time;
        private final int weight;

        public Truck(int duration, int weight) {
            this.time = duration;
            this.weight = weight;
        }

        public int getTime() {
            return time;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Truck{" +
                    "time=" + time +
                    ", weight=" + weight +
                    '}';
        }
    }
}