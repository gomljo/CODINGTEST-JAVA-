import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static final String DODO_WIN = "do";
    private static final String SUYEON_WIN = "su";
    private static final String DRAW = "dosu";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfCard = Integer.parseInt(st.nextToken());
        int numberOfStage = Integer.parseInt(st.nextToken());
        Deque<Integer> deckOfDodo = new LinkedList<>();
        Deque<Integer> deckOfSuYeon = new LinkedList<>();
        for (int i = 0; i < numberOfCard; i++) {
            st = new StringTokenizer(br.readLine());
            int cardOfDodo = Integer.parseInt(st.nextToken());
            int cardOfSuyeon = Integer.parseInt(st.nextToken());
            deckOfDodo.offerFirst(cardOfDodo);
            deckOfSuYeon.offerFirst(cardOfSuyeon);
        }
        Deque<Integer> groundOfDodo = new LinkedList<>();
        Deque<Integer> groundOfSuyeon = new LinkedList<>();
        int turn = 0;
        while (numberOfStage > turn) {
            if (turn % 2 == 0) {
                groundOfDodo.offerLast(deckOfDodo.pollFirst());

            } else {
                groundOfSuyeon.offerLast(deckOfSuYeon.pollFirst());

            }
            if (deckOfDodo.isEmpty()) {
                break;
            }
            if (deckOfSuYeon.isEmpty()) {
                break;
            }
            if ((!groundOfDodo.isEmpty() && groundOfDodo.peekLast() == 5) || (!groundOfSuyeon.isEmpty() && groundOfSuyeon.peekLast() == 5)) {
                deckOfDodo.addAll(groundOfSuyeon);
                groundOfSuyeon.clear();
                deckOfDodo.addAll(groundOfDodo);
                groundOfDodo.clear();
            }

            if ((!groundOfDodo.isEmpty() && !groundOfSuyeon.isEmpty())
                    && groundOfDodo.peekLast() + groundOfSuyeon.peekLast() == 5) {
                deckOfSuYeon.addAll(groundOfDodo);
                groundOfDodo.clear();
                deckOfSuYeon.addAll(groundOfSuyeon);
                groundOfSuyeon.clear();
            }
            turn++;
        }
        String answer = judge(deckOfDodo, deckOfSuYeon);
        System.out.println(answer);
        
    }

    public static String judge(Deque<Integer> deckOfDodo, Deque<Integer> deckOfSuyeon) {
        if (deckOfDodo.size() > deckOfSuyeon.size()) {
            return DODO_WIN;
        }
        if (deckOfDodo.size() < deckOfSuyeon.size()) {
            return SUYEON_WIN;
        }
        return DRAW;
    }

}