import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfObjects = Integer.parseInt(st.nextToken());
        int weightLimit = Integer.parseInt(st.nextToken());
        int[][] objects = new int[numberOfObjects][2];
        int maxWeight = 0;
        for (int i = 0; i < numberOfObjects; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            objects[i] = new int[]{weight, value};
            maxWeight = Math.max(maxWeight, weight);
        }
        Arrays.sort(objects, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int[][] bag = new int[numberOfObjects + 1][weightLimit + 1];

        for (int i = 1; i <= numberOfObjects; i++) {
            for (int j = 1; j <= weightLimit; j++) {
                if(objects[i-1][0] <= j){
                    bag[i][j] = Math.max(objects[i-1][1] + bag[i-1][j - objects[i-1][0]], bag[i-1][j]);
                }
                else {
                    bag[i][j] = bag[i-1][j];
                }
            }
        }
        System.out.println(bag[numberOfObjects][weightLimit]);
        br.close();
    }
}