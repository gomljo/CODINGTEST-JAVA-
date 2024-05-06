import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        String word = scanner.next();
        WordShuffler shuffler = new WordShuffler(word, x);
        shuffler.findPeriod();
        shuffler.findPattern();
        shuffler.printResult();
        scanner.close();
    }
}

class WordShuffler {
    private String word;
    private int x;
    private String[] result;
    private final List<String> resultList;

    public WordShuffler(String word, int x) {
        this.word = word;
        this.x = x;
        this.resultList = new ArrayList<>();
    }

    public void findPeriod() {
        String target = this.word;
        this.resultList.add(target);
        int count = this.x;
        while (count> 0) {
            StringBuilder front = new StringBuilder();
            StringBuilder rear = new StringBuilder();
            if (this.word.length() % 2 == 0) {
                target = shuffleEachEven(front, rear, target);
            } else {
                target = shuffleEachOdd(front, rear, target);
            }
            if(this.resultList.contains(target)){
                break;
            }
            this.resultList.add(target);
            count--;
        }
    }

    public void findPattern() {
        int index = this.x % this.resultList.size();
        this.result = this.resultList.get(index).split("");
    }

    private String shuffleEachEven(StringBuilder front, StringBuilder rear, String target) {
        for (int i = 0; i < target.length(); i += 2) {
            front.append(target.charAt(i));
        }
        for (int i = target.length() - 1; i >= 0; i -= 2) {
            rear.append(target.charAt(i));
        }
        return front.append(rear).toString();
    }

    private String shuffleEachOdd(StringBuilder front, StringBuilder rear, String target) {
        for (int i = 0; i < target.length(); i += 2) {
            front.append(target.charAt(i));
        }
        for (int i = target.length() - 2; i >= 0; i -= 2) {
            rear.append(target.charAt(i));
        }
        return front.append(rear).toString();
    }

    public void printResult() {
        System.out.println(String.join("", this.result));
    }

    public void printPeriod() {
        for (String strings : resultList) {
            System.out.println(strings);
        }
    }
}