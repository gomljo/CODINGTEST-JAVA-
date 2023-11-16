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
			HashSet<Integer> cases = new HashSet<>();
			cases.add(0);
			int numberOfProblems = sc.nextInt();
			int[] scores = new int[numberOfProblems];
			for(int i=0; i< numberOfProblems; i++) {
				scores[i] = sc.nextInt();
			}
			for(int i=0; i< numberOfProblems; i++) {
				HashSet<Integer> temp = new HashSet<>();
				temp.addAll(cases);
				Iterator<Integer> it = temp.iterator();
				while(it.hasNext()) {
					cases.add(it.next()+scores[i]);
				}
			}
            System.out.printf("#%d %d\n", test_case, cases.size());
		}
	}
}