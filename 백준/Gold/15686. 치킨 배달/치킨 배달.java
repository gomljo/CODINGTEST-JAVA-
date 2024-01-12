import java.io.*;
import java.util.*;
 
public class Main {
	static StringTokenizer st;
	static int N, M;
	static int ans = Integer.MAX_VALUE;
	static int[][] chickenGetList;
	static List<int[]> house;
	static List<int[]> chicken;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new LinkedList<>();
		house = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp == 2) {
					int[] temp2 = {i, j};
					chicken.add(temp2);
				} else if (temp == 1) {
					int[] temp1 = {i,j};
					house.add(temp1);
				}
			}
		}
		chickenGetList = new int[chicken.size()][2];
		comb(0,0);
		System.out.println(ans);
	}
	
	public static void comb(int cnt, int start) {
		if (cnt == M) {
			diffDistance();
			return;
		}
 
		for (int i = start; i < chicken.size(); i++) {
			chickenGetList[cnt][0] = chicken.get(i)[0];
			chickenGetList[cnt][1] = chicken.get(i)[1];
			comb(cnt + 1, i + 1);
		}
		
	}
	
	public static void diffDistance() {
		int minDistance = 0;
 
		for (int i = 0; i < house.size(); i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++)	{
				min = Math.min(min, Math.abs(chickenGetList[j][0] - house.get(i)[0]) + 
						            Math.abs(chickenGetList[j][1] - house.get(i)[1]));
			}
			minDistance += min;
		}
		ans = Math.min(ans, minDistance);
		return;
	}
}