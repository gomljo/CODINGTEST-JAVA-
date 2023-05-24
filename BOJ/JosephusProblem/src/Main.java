import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> table = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            table.add(i+1);
        }

        int index = 0;
        List<String> permutation = new ArrayList<>();
        while (table.size()!=0){
            int divisor = table.size();
            int value = table.get((index+K-1)%(divisor));
            permutation.add(String.valueOf(value));
            table.remove((index+K-1)%(divisor));
            index = (index+K-1)%(divisor);
        }
        System.out.print("<");
        System.out.print(String.join(", ", permutation));
        System.out.print(">\n");
    }
}
