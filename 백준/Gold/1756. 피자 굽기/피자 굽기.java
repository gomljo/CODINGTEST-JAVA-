import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ovenHeight = Integer.parseInt(st.nextToken());
        int numberOfDough = Integer.parseInt(st.nextToken());
        int[] ovens = new int[ovenHeight];
        int[] doughs = new int[numberOfDough];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < ovenHeight; i++) {
            ovens[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfDough; i++) {
            doughs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < ovenHeight; i++) {
            ovens[i] = Math.min(ovens[i], ovens[i-1]);
        }
        int doughIndex = 0;
        int depth = 0;
        for (int i = ovenHeight - 1; i >= 0 ; i--) {

            if(doughIndex >= numberOfDough){
                break;
            }

            if(ovens[i] >= doughs[doughIndex]){
                doughIndex++;
                depth = i;
            }
        }
        System.out.println(doughIndex == numberOfDough ? depth+1: 0);
        br.close();
    }

}