import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
public class Solution {

	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int maxNumber = sc.nextInt();
		List<String> threeSixNine = new ArrayList<>();
		threeSixNine.add("3");
		threeSixNine.add("6");
		threeSixNine.add("9");
		for(int i=1; i <= maxNumber; i++) {
			String[] numbers = String.valueOf(i).split("");
			int numberOfMatch = 0;
			for(int j=0; j<numbers.length; j++) {
				if(threeSixNine.contains(numbers[j])) {
					numberOfMatch+=1;
				}
			}
			
			if(numberOfMatch==0) {
				System.out.printf("%d ", i);
			}
			else {
				StringBuilder sb = new StringBuilder();
				String clap = "-";
				
				for(int i1=0; i1<numberOfMatch; i1++) {
					sb.append(clap);
				}
				
				System.out.printf("%s ", sb.toString());
			}
			
		}

	}
}