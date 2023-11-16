import java.io.FileInputStream;
import java.util.*;
class Solution
{
	static int max;
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int numberOfNumbers = sc.nextInt();
			List<Integer> numbers = new ArrayList<>();
			for(int i=0; i<numberOfNumbers; i++) {
				numbers.add(sc.nextInt());
			}
			Collections.sort(numbers, Comparator.reverseOrder());
			max = -1;
			for(int i=0; i < numberOfNumbers; i++) {
				for(int j=i+1; j < numberOfNumbers; j++) {
					increase(numbers.get(i)*numbers.get(j));
					
				}
			}
			System.out.printf("#%d %d\n", test_case, max);

		}
	}
	public static void increase(int num) {
        boolean up = true;
        String s = Integer.toString(num);

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) - '0' > (s.charAt(i + 1) - '0')) { //앞의 수가 더 크면 단조증가수 아님
               up = false;
                break;
            }
        }
        if (up) { //max update
            max = Math.max(num,max);
        }
    }
}