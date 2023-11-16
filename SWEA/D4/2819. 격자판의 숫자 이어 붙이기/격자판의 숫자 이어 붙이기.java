import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
	static int[] dRow = new int[] {0,0,1,-1};
	static int[] dCol = new int[] {1,-1,0,0};
	static int maxMove = 6;
	static int size = 4;
	static int[][] matrix = new int[size][size];
	static HashSet<String> set = new HashSet<>();
	
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
            set.clear();
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					generateNumber(0, i, j, String.valueOf(matrix[i][j]));
				}
			}
			System.out.printf("#%d %d\n", test_case, set.size());
		}
	}
	public static void generateNumber(int move, int row, int col, String number) {
		
		if(move==maxMove) {
			set.add(number);
			return;
		}
		
		for(int i=0; i < 4; i++) {
			int newRow = row + dRow[i];
			int newCol = col + dCol[i];
			
			if(newRow < 0 || newRow > size-1 || newCol < 0 || newCol > size-1) {
				continue;
			}
			generateNumber(move+1, newRow, newCol, number+String.valueOf(matrix[newRow][newCol]));
		}
	}
}