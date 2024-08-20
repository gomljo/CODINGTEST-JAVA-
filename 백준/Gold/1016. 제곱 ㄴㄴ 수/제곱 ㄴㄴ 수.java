import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        int range = (int) (max-min+1);
        boolean[] checks = new boolean[range];
        for (long i = 2; i <= (long) Math.sqrt(max); i++) {
            long squared = i*i;
            long start = min % squared == 0 ? min / squared : (min / squared) + 1;
            for(long j = start; j * squared <= max; j ++) {
                checks[(int) ( (j * squared) - min)] = true;
            }
        }

        int count =0;
        for (int i = 0; i < range; i++) {
            if(!checks[i]){
                count++;
            }
        }
        System.out.println(count);

    }
}