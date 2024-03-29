import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<List<String>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();
            List<String> child = List.of(left, right);
            graph.get(root.charAt(0) - 'A').addAll(child);
        }
        preOrder("A");
        System.out.println();
        inOrder("A");
        System.out.println();
        postOrder("A");
        br.close();
    }

    public static void preOrder(String value) {
        if(value.equals(".")){
            return;
        }
        System.out.printf("%s", value);

        if (!graph.get(value.charAt(0) - 'A').isEmpty()) {
            preOrder(graph.get(value.charAt(0) - 'A').get(0));
        }

        if (graph.get(value.charAt(0) - 'A').size() > 1) {
            preOrder(graph.get(value.charAt(0)-'A').get(1));
        }

    }

    public static void inOrder(String value) {

        if(!graph.get(value.charAt(0) - 'A').isEmpty()){
            if(!graph.get(value.charAt(0) - 'A').get(0).equals(".")){
                inOrder(graph.get(value.charAt(0) - 'A').get(0));
            }

        }
        if(!value.equals(".")){
            System.out.printf("%s", value);
        }

        if(graph.get(value.charAt(0) - 'A').size() > 1){
            if(!graph.get(value.charAt(0)-'A').get(1).equals(".")){
                inOrder(graph.get(value.charAt(0)-'A').get(1));
            }
        }
    }

    public static void postOrder(String value) {
        if(!graph.get(value.charAt(0) - 'A').isEmpty()){
            if(!graph.get(value.charAt(0) - 'A').get(0).equals(".")){
                postOrder(graph.get(value.charAt(0) - 'A').get(0));
            }

        }
        if(graph.get(value.charAt(0) - 'A').size() > 1){
            if(!graph.get(value.charAt(0)-'A').get(1).equals(".")){
                postOrder(graph.get(value.charAt(0)-'A').get(1));
            }
        }
        if(!value.equals(".")){
            System.out.printf("%s", value);
        }
    }

}