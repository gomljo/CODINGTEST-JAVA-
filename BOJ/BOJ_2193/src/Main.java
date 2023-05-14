import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Long> dp = new ArrayList<>();
        dp.add(1L);
        dp.add(1L);

        for (int i = 2; i <=n; i++) {
            dp.add(dp.get(i-1) + dp.get(i-2));
        }
        System.out.println(dp.get(n-1));
    }
}
