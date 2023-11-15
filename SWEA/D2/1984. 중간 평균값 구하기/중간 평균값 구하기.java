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
			int[] numbers = new int[10];
			for(int i=0; i<10; i++) {
				numbers[i] = sc.nextInt();
			}
			Arrays.sort(numbers);
			int sum = Arrays.stream(numbers).sum();
			sum -= numbers[0];
			sum -= numbers[9];
			double avg = (double) sum / 8;
			System.out.printf("#%d %d\n", test_case, Math.round(avg));
		}
	}
}