import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] stations = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            stations[i] = Integer.parseInt(st.nextToken());
        }
        stations[0] = 0;
        stations[n+1] = L;
        Arrays.sort(stations);
        // 휴게소 간의 최대 값의 최소를 매개변수 탐색을 하되 이분 탐색을 하자
        int min = 1;
        int max = L;
        while (min <= max) {
            int mid = (min + max) / 2;
            int count = 0;
            for (int i = 1; i < stations.length; i++) {
                count += (stations[i] - stations[i-1] - 1) / mid;
            }

            if (count > m) {
                min = mid + 1;
            } else{
                max = mid - 1;
            }
        }
        System.out.println(min);
    }
}