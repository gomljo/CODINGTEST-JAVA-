import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_25556 {

    public static int getMaxValueIndex(List<List<Integer>> stacks){
        int maxValue = 0;
        int maxIdx = 0;
        for (int i = 0; i < stacks.size(); i++){
            if (stacks.get(i).get(0) > maxValue){
                maxIdx = i;
                maxValue = stacks.get(i).get(0);
            }
        }
        return maxIdx;
    }

    public static List<List<Integer>> initialize(){
        List<List<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Integer> stack = new ArrayList<>();
            stack.add(0);
            stacks.add(stack);
        }
        return stacks;
    }

    public static String isPossibleToCleaning(int[] combination){

        List<List<Integer>> stacks = initialize();

        for (int j : combination) {
            boolean flag = false;
            for (List<Integer> stack : stacks) {
                if (j > stack.get(0)) {
                    stack.add(0, j);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return "NO";
            }
        }
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size()!=combination.length){
            int index = getMaxValueIndex(stacks);
            numbers.add(stacks.get(index).remove(0));
        }
        int[] sortedCombination = Arrays.stream(combination).sorted().toArray();
        for (int i = 0; i < sortedCombination.length; i++) {
            if(sortedCombination[i]!=numbers.get(numbers.size()-1-i)){
                return "NO";
            }
        }
        return "YES";
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] combinations = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < combinations.length; i++) {
            combinations[i] = Integer.parseInt(st.nextToken());
        }
        String result = isPossibleToCleaning(combinations);
        System.out.println(result);
    }
}
