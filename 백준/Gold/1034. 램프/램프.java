import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 그리디?
    // 시뮬레이션?
    // 행의 켜진 갯수?
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rowSize = scanner.nextInt();
        int columnSize = scanner.nextInt();
        String[] table = new String[rowSize];
        for (int i = 0; i < rowSize; i++) {
            table[i] = scanner.next();
        }
        int numberOfClick = scanner.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < rowSize; i++) {
            String row = table[i];
            map.put(row, map.getOrDefault(row, 0) + 1);
        }
        List<String> keys = new ArrayList<>(map.keySet());
        int answer = 0;
        int evenOrOdd = numberOfClick % 2;
        for (int i = 0; i < keys.size(); i++) {
            String[] key = keys.get(i).split("");
            int numberOfOff = 0;
            for (int j = 0; j < key.length; j++) {
                if (key[j].equals("0")) {
                    numberOfOff++;
                }
            }
            if (numberOfOff % 2 != evenOrOdd) {
                continue;
            }
            if (numberOfOff <= numberOfClick) {
                answer = Math.max(answer, map.get(keys.get(i)));
            }
        }
        System.out.println(answer);
        scanner.close();
    }

    public static int countActivateRow(int[][] table) {
        int activeRowCount = 0;
        for (int i = 0; i < table[0].length; i++) {
            int activeCount = 0;
            for (int j = 0; j < table.length; j++) {
                if (table[j][i] == 1) {
                    activeCount++;
                }
            }
            if (activeCount == table[0].length) {
                activeRowCount++;
            }
        }
        return activeRowCount;
    }

    public static int pushSwitch(int column, int[][] table) {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][column] == 1) {
                table[i][column] = 0;
            } else {
                table[i][column] = 1;
                count++;
            }
        }
        return count;
    }
}