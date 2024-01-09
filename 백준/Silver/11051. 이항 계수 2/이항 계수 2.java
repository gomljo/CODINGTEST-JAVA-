import java.util.Scanner;

public class Main {

    private static final int[][] coefficient = new int[1001][1001];

    public static int find(int n, int k) {

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(k, i); j++) {
                if((j==0) || (j==i)){
                    coefficient[i][j] = 1;
                }
                else {
                    coefficient[i][j] = (coefficient[i-1][j-1] + coefficient[i-1][j]) % 10007;
                }
            }
        }
        return coefficient[n][k] ;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        System.out.println(find(N,K));


    }
}