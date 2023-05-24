import java.util.List;

public class test {
    static List<int[]> sequenceList;
    static boolean[] visited;
    // [true, true, true, ...]
    static void dfs(int start, int depth, int[] sequence, int[] numbers){

        // 종료 조건
        if(depth==sequence.length){
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                sequence[depth] = numbers[i];
                dfs(i+1, depth+1, sequence, numbers);
                visited[i] = false;
            }
        }
    }

    public int[] solution(int[] numbers, int n , int r){
        // 조합을 구하는 프로그램
        int[] sequence = new int[r];
        dfs(0, 0, sequence, numbers);
        return sequence;
    }


}



