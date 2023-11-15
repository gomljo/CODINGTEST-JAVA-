import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);

		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int testNum = sc.nextInt();
			int[][] matrix = new int[100][100];
			
			for(int i=0; i< 100; i++) {
				
				for(int j=0; j<100; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			int maxValue=0;
			// 각 행 합 구하기
			for(int row=0; row < 100; row++) {
				maxValue = Math.max(maxValue, Arrays.stream(matrix[row]).sum());
			}
			// 각 열 합 구하기
			for(int col=0; col< 100; col++) {
				int sum = 0;
				for(int row=0; row<100; row++) {
					sum += matrix[row][col];
				}
				maxValue = Math.max(maxValue, sum);			
			}
			// 대각선 구하기 1
			for(int col=0; col< 100; col++) {
				int sum = 0;
				sum += matrix[col][col];
				maxValue = Math.max(maxValue, sum);			
			}
			// 대각선 구하기 2
			for(int col=0; col< 100; col++) {
				int sum = 0;
				sum += matrix[col][99-col];
				maxValue = Math.max(maxValue, sum);			
			}
			System.out.printf("#%d %d\n", testNum, maxValue);
		}
	}
}