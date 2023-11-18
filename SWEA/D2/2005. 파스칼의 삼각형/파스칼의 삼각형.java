import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			
			int[][] pascal = new int[10][11];
			
			for(int i=0;i<N;i++) {
				pascal[i][1] = 1;
			}
			for(int i=1; i<N; i++) {
				for(int j=1; j<=i+1; j++) {
					pascal[i][j] = pascal[i-1][j-1]+pascal[i-1][j];
				}
			}
			System.out.printf("#%d\n", test_case);
			for(int i=0; i<N; i++) {
				for(int j=1; j<=i+1; j++) {
					System.out.printf("%d ", pascal[i][j]);
				}
				System.out.println();
			}
			
		}
	}
}