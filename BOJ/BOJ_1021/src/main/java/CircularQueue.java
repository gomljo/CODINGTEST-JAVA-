import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class CircularQueue {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i <n ; i++) {
            deque.offer(i+1);
        }
        int[] seq = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int count=0;

        for (int i = 0; i < m; i++) {

            int targetIdx = deque.indexOf(seq[i]);
            int halfIdx;

            if(deque.size()%2==0){
                halfIdx = deque.size() / 2 - 1;
            }
            else {
                halfIdx = deque.size() / 2;
            }

            if(targetIdx <= halfIdx){
                for(int j = 0; j < targetIdx; j++) {
                    int temp = deque.pollFirst();
                    deque.offerLast(temp);
                    count++;
                }
            }
            else{
                for (int j = 0; j < deque.size() - targetIdx; j++) {
                    int temp = deque.pollLast();
                    deque.offerFirst(temp);
                    count++;
                }
            }
            deque.pollFirst();
        }
        System.out.println(count);
    }

}
