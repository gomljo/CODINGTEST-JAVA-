import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxHeight = Integer.parseInt(st.nextToken());
        int maxLength = Integer.parseInt(st.nextToken());
        int[] contourLine = new int[maxLength];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < maxLength; i++) {
            contourLine[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        for (int i = 1; i < maxLength; i++) {
            int left = 0;
            int right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, contourLine[j]);
            }

            for (int j = i + 1; j < maxLength; j++) {
                right = Math.max(right, contourLine[j]);
            }

            int height = Math.min(left, right);

            if (contourLine[i] < height) {
                answer += height - contourLine[i];
            }
        }
        System.out.println(answer);
    }
}