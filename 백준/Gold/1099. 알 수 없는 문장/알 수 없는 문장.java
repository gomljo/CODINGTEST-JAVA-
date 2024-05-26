import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sentence = scanner.next();
        int numberOfWords = scanner.nextInt();
        HashMap<Integer, List<Word>> words = new HashMap<>();

        for (int i = 0; i < numberOfWords; i++) {
            String word = scanner.next();
            if (word.length() > sentence.length()) {
                continue;
            }
            if (!words.containsKey(word.length())) {
                words.put(word.length(), new ArrayList<>());
            }
            words.get(word.length()).add(new Word(new ArrayList<>(List.of(word.split(""))), word));
        }
        int[][] dp = new int[sentence.length() + 1][sentence.length() + 1];
        for (int i = 0; i <= sentence.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int length = 1; length <= sentence.length(); length++) {
            for (int start = 0; start < sentence.length() + 1 - length; start++) {
                if (words.containsKey(length)) {
                    List<Word> wordList = words.get(length);
                    String compare = sentence.substring(start, start + length);
                    for (Word word : wordList) {

                        if (!word.isSameElement(compare)) {
                            continue;
                        }
                        int cost = word.calculateCost(compare);

                        if (dp[start][start + length] == -1 || cost < dp[start][start + length]) {
                            dp[start][start + length] = cost;
                        }
                    }
                }

                if (length > 1) {
                    for (int mid = 1; mid < length; mid++) {
                        int prevCost = dp[start][start + mid];
                        int nextCost = dp[start + mid][start + length];
                        if (prevCost != -1 && nextCost != -1) {
                            int totalCost = prevCost + nextCost;
                            if ((totalCost < dp[start][start + length]) || (dp[start][start + length] == -1)) {
                                dp[start][start + length] = totalCost;
                            }
                        }
                    }
                }
            }

        }

        System.out.println(dp[0][sentence.length()]);
        scanner.close();
    }
}

class Word {
    private final List<String> letterSet;
    private final String value;

    public Word(List<String> letterSet, String value) {
        this.letterSet = letterSet.stream().sorted().collect(Collectors.toList());
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public List<String> getLetterSet() {
        return letterSet;
    }

    public boolean isSameElement(String word) {
        List<String> letters = Arrays.stream(word.split("")).sorted().collect(Collectors.toList());
        if (word.length() != letterSet.size()) {
            return false;
        }
        for (int i = 0; i < letters.size(); i++) {
            if (!letters.get(i).equals(letterSet.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int calculateCost(String word) {
        int cost = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != word.charAt(i)) {
                cost++;
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        return "Word{" +
                "letterSet=" + letterSet +
                ", value='" + value + '\'' +
                '}';
    }
}