import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();


		for(int test_case = 1; test_case <= T; test_case++)
		{
			int answer = 0;
			int size = sc.nextInt();
			int[][] farm = new int[size][size];
			for(int i=0; i < size; i++) {
				String[] row = sc.next().split("");
				farm[i] = Arrays.stream(row).map(Integer::valueOf).mapToInt(plant -> plant).toArray();
			}
			int half = size / 2;
			for(int i=0; i <= half ; i++) {
				answer += farm[i][half];
				for(int j=1; j <= i; j++) {
					answer += farm[i][half+j];
					answer += farm[i][half-j];
				}
			}
			
			for(int i=half+1; i < size ; i++) {
				answer += farm[i][half];
				for(int j=size-1 - i; j > 0; j--) {
					answer += farm[i][half + (j)];
					answer += farm[i][half - (j)];
				}
			}
			System.out.printf("#%d %d\n", test_case, answer);
		}
	}
}