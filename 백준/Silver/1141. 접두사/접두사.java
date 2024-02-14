import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfWord = sc.nextInt();
        String[] words = new String[numberOfWord];
        for (int i = 0; i < numberOfWord; i++) {
            words[i] = sc.next();
        }
        Arrays.sort(words, Comparator.reverseOrder());
        List<String> notPrefix = new ArrayList<>();

        for (int i = 0; i < numberOfWord; i++) {
            if(isNotPrefix(notPrefix, words[i])){
                notPrefix.add(words[i]);
            }
        }
        System.out.println(notPrefix.size());
    }

    public static boolean isNotPrefix(List<String> notPrefix, String word) {
        for (String element: notPrefix) {
            if(element.startsWith(word)){
                return false;
            }
        }
        return true;
    }
}