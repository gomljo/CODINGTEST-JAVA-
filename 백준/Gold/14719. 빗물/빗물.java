import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] params = br.readLine().split(" ");
        int H = Integer.parseInt(params[0]);
        int W = Integer.parseInt(params[1]);
        int answer = 0;
        int[] blocks = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .mapToInt(block -> block)
                .toArray();
        for (int i = 1; i < W - 1; i++) {
            int left = 0;
            int right = 0;

            for (int j = 0; j <= i; j++) {
                left = Math.max(left, blocks[j]);
            }

            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, blocks[j]);
            }
            if(blocks[i] < left && blocks[i] < right){
                answer += Math.min(left, right) - blocks[i];
            }

        }

        System.out.println(answer);
    }
}