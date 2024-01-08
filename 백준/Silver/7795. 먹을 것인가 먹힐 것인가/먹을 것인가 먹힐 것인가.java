import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int binarySearch_upperBound(int target, int[] search) {
        int start = 0;
        int end = search.length;
        int mid;
        while (start < end) {
            mid = (start + end) / 2;

            if (search[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfTestcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfTestcase; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numberOfPredator = Integer.parseInt(st.nextToken());
            int numberOfPrey = Integer.parseInt(st.nextToken());

            int[] predators = new int[numberOfPredator];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numberOfPredator; j++) {
                predators[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(predators);
            int[] preys = new int[numberOfPrey];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numberOfPrey; j++) {
                preys[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(preys);

            int answer =  0;
            for (int prey : preys) {
                int index = binarySearch_upperBound(prey, predators);
                answer += predators.length - index;
            }
            System.out.println(answer);
        }
    }
}