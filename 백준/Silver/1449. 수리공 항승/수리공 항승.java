import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfHoles = Integer.parseInt(st.nextToken());
        int tapeLength = Integer.parseInt(st.nextToken());

        int[] holes = new int[numberOfHoles];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < numberOfHoles; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }
        // 안되면 뒤에부터 찾자
        Arrays.sort(holes);
        int answer = 1;
        double startPoint = holes[0] - 0.5;
        for (int i = 1; i < numberOfHoles; i++) {
            double diff = holes[i]+0.5 - startPoint;
            if((int) diff > tapeLength){
                startPoint = holes[i] - 0.5;
                answer++;
            }
        }
        System.out.println(answer);
    }
}