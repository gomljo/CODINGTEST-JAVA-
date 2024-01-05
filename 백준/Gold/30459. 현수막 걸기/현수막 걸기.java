import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] polls;
    private static int[] picks;

    public static int binarySearch(int height) {
        int start = 0;
        int end = polls.length;
        while (start < end) {
            int mid = (start + end) / 2;

            if (polls[mid] <= height) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfPick = Integer.parseInt(st.nextToken());
        int numberOfPoll = Integer.parseInt(st.nextToken());
        int maxCardArea = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        picks = new int[numberOfPick];
        for (int i = 0; i < numberOfPick; i++) {
            picks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(picks);
        polls = new int[numberOfPoll];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfPoll; i++) {
            polls[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(polls);
        int maxArea = 0;
        for (int i = 0; i < picks.length; i++) {
            for (int j = i + 1; j < picks.length; j++) {

                int underLine = picks[j] - picks[i];
                int height = maxCardArea * 2 / underLine;
                int idx = binarySearch(height)-1;
                if (idx == -1) {
                    continue;
                }
                maxArea = Math.max(maxArea, polls[idx] * underLine);
            }
        }
        if(maxArea==0){
            System.out.println("-1");
        }
        else {
            System.out.printf("%.1f", maxArea / 2.0);
        }
        
    }
}