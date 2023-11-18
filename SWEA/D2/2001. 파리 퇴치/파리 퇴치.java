import java.util.Arrays;
import java.util.Scanner;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{	
	static int M;
	static int N;
	static int[][] room;
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] params = br.readLine().split(" ");
			M = Integer.parseInt(params[0]);
			N = Integer.parseInt(params[1]);
			room = new int[M][M];
			
			for(int i=0; i<M; i++) {
				room[i] = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).mapToInt(fly -> fly).toArray();
			}
			int maxKilledFly = 0;
			int maxRange = M - N + 1;
			for(int i=0; i<maxRange; i++) {
				for(int j=0; j<maxRange;j++) {
					maxKilledFly = Math.max(maxKilledFly, killFly(i, j));
				}
			}
			System.out.printf("#%d %d\n", test_case, maxKilledFly);
		
		}
	}
	
	public static int killFly(int startRow, int startCol) {
		int numberOfKilledFly = 0;
		for(int row=startRow; row<startRow+N; row++) {
			for(int col=startCol; col < startCol+N; col++) {
				numberOfKilledFly += room[row][col];
			}
		}
		return numberOfKilledFly;
		
	}
}