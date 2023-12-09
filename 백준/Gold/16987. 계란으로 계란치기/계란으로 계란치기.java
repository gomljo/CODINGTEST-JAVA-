import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int numberOfBrokenEgg = 0;
    private static int[][] eggs;
    private static int NUMBER_OF_EGG;

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
                        } else {
                            simulate(depth + 1, modifiedEggs, crashed);
                        }
                    } else {
                        simulate(depth + 1, modifiedEggs, crashed);
                    }
                }
            }
        }
    }

    public static void simulateBetter(int idx, int cnt) {

        if (idx == NUMBER_OF_EGG) {
            numberOfBrokenEgg = Math.max(numberOfBrokenEgg, cnt);
            return;
        }

        if(eggs[idx][0] <= 0 || cnt == NUMBER_OF_EGG-1) {
            // 다음 계란을 들어 봄
            simulateBetter(idx + 1, cnt);
            return;
        }
        int currentCnt = cnt;
        for (int i = 0; i < NUMBER_OF_EGG; i++) {

            if (i == idx) {
                continue;
            }

            if (eggs[i][0] <= 0) {
                continue;
            }

            crash(idx, i);

            if (eggs[idx][0] <= 0) {
                cnt++;
            }

            if (eggs[i][0] <= 0){
                cnt++;
            }
            simulateBetter(idx+1, cnt);
            recovery(idx, i);
            cnt = currentCnt;

        }


    }

    public static void recovery(int currentEggIndex, int otherEggIndex) {
        eggs[currentEggIndex][0] += eggs[otherEggIndex][1];
        eggs[otherEggIndex][0] += eggs[currentEggIndex][1];
    }

    public static void crash(int currentEggIndex, int otherEggIndex) {
        eggs[currentEggIndex][0] -= eggs[otherEggIndex][1];
        eggs[otherEggIndex][0] -= eggs[currentEggIndex][1];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        NUMBER_OF_EGG = Integer.parseInt(br.readLine());
        eggs = new int[NUMBER_OF_EGG][2];
        for (int i = 0; i < NUMBER_OF_EGG; i++) {
            eggs[i] = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::valueOf)
                    .mapToInt(property -> property)
                    .toArray();
        }
        simulateBetter(0, 0);
        System.out.println(numberOfBrokenEgg);
    }
}