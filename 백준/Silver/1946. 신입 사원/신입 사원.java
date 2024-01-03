import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfCase; i++) {
            int numberOfVolunteer = Integer.parseInt(br.readLine());
            List<Score> scoreList = new ArrayList<>();
            for (int j = 0; j < numberOfVolunteer; j++) {
                int[] score = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).mapToInt(s -> s).toArray();
                scoreList.add(new Score(score[0], score[1]));
            }

            scoreList.sort(Comparator.comparing(Score::getDocument).thenComparing(Score::getInterview));
            Score current = scoreList.get(0);
            int successfulCandidates = 0;
            for (Score score: scoreList) {
                if(score.getDocument() > current.getDocument() && score.getInterview() > current.getInterview()){
                    continue;
                }
                successfulCandidates++;
                current = score;
            }
            System.out.println(successfulCandidates);
        }
    }
}

class Score {
    private final int document;
    private final int interview;

    public Score(int document, int interview) {
        this.document = document;
        this.interview = interview;
    }

    public int getDocument() {
        return document;
    }

    public int getInterview() {
        return interview;
    }

    @Override
    public String toString() {
        return "Score{" +
                "document=" + document +
                ", interview=" + interview +
                '}';
    }
}