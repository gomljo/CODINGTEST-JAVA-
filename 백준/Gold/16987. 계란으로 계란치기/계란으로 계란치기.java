import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int numberOfBrokenEgg = 0;

    public static void simulate(int depth, int[][] eggs, int crashed) {
        if (depth == eggs.length) {
            numberOfBrokenEgg = Math.max(numberOfBrokenEgg, crashed);
            return;
        }

        if (eggs[depth][0] <= 0) {
            int[][] modifiedEggs = new int[eggs.length][2];
            for (int j = 0; j < eggs.length; j++) {
                modifiedEggs[j] = eggs[j].clone();
            }
            simulate(depth + 1, modifiedEggs, crashed);
        } else {
            for (int i = 0; i < eggs.length; i++) {

                if (i != depth) {
                    int[][] modifiedEggs = new int[eggs.length][2];
                    for (int j = 0; j < eggs.length; j++) {
                        modifiedEggs[j] = eggs[j].clone();
                    }
                    if (eggs[i][0] > 0) {
                        modifiedEggs[i][0] -= modifiedEggs[depth][1];
                        modifiedEggs[depth][0] -= modifiedEggs[i][1];
                        if (modifiedEggs[i][0] <= 0 && modifiedEggs[depth][0] <= 0) {
                            simulate(depth + 1, modifiedEggs, crashed + 2);
                        } else if (modifiedEggs[i][0] <= 0 && modifiedEggs[depth][0] > 0) {
                            simulate(depth + 1, modifiedEggs, crashed + 1);
                        } else if (modifiedEggs[i][0] > 0 && modifiedEggs[depth][0] <= 0) {
                            simulate(depth + 1, modifiedEggs, crashed + 1);
                        } else {;
                            simulate(depth + 1, modifiedEggs, crashed);
                        }
                    } else {
                        simulate(depth + 1, modifiedEggs, crashed);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfEgg = Integer.parseInt(br.readLine());
        if (numberOfEgg > 1) {
            int[][] eggs = new int[numberOfEgg][2];
            for (int i = 0; i < numberOfEgg; i++) {
                eggs[i] = Arrays.stream(br.readLine().split(" "))
                        .map(Integer::valueOf)
                        .mapToInt(property -> property)
                        .toArray();
            }
            simulate(0, eggs, 0);


        }
        System.out.println(numberOfBrokenEgg);
    }
}