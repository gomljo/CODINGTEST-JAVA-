import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> parentheses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parentheses.add(br.readLine());
        }

        // check parentheses have pair

        for(String bracket: parentheses){
            LinkedList<String> stack = new LinkedList<>();
            for (String b: bracket.split("")){
                if(stack.isEmpty()){
                    stack.offerFirst(b);
                    continue;
                }
                if(b.equals("(")){
                    stack.offerFirst(b);
                }
                else {
                    if(stack.peekFirst().equals("(")){
                        stack.pollFirst();
                    }
                }
            }
            // output print
            if(!stack.isEmpty()){
                System.out.println("NO");
            }else {
                System.out.println("YES");
            }
        }
    }
}
