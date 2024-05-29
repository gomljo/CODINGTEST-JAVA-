import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Word> wordList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            wordList.add(new Word((char) (i + 'A'), 0, 0, 0));
        }
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            String word = scanner.next();
            words[i] = word;
            for (int j = 0; j < word.length(); j++) {
                Word current = wordList.get(word.charAt(j) - 'A');
                current.changePosition(word.length() - j);
                current.plusCount((int) Math.pow(10,word.length()-j));
                if(j==0){
                    current.setIsFirst();
                }
            }
        }
        wordList.sort(Comparator.comparing(Word::getCount).thenComparing(Word::getPosition).thenComparing(Word::getIsFirst));
        int number = 9;
        for (Word word : wordList) {
            if (word.getCount() != 0) {
                word.setNumber(number);
                number--;
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                for (int k = 0; k < wordList.size(); k++) {
                    if (wordList.get(k).getValue() == word.charAt(j)) {
                        sb.append(wordList.get(k).getNumber());
                    }
                }

            }
            answer += Integer.parseInt(sb.toString());
        }
        System.out.println(answer);

        scanner.close();
    }

}

class Word {
    private final char value;
    private int count;
    private int position;
    private int number;

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst() {
        this.isFirst = -1;
    }

    private int isFirst;

    public Word(char value, int count, int position, int isFirst) {
        this.count = count;
        this.position = position;
        this.value = value;
        this.isFirst = isFirst;
    }

    public int getCount() {
        return count;
    }

    public int getPosition() {
        return position;
    }

    public void plusCount(int position) {
        this.count-=position;
    }

    public void changePosition(int position) {
        this.position = Math.min(-1 * position, this.position);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value=" + value +
                ", count=" + count +
                ", position=" + position +
                ", number=" + number +
                '}';
    }
}