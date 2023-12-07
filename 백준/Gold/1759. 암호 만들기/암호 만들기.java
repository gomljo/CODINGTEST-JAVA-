import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    private final static List<Character> VOWEL = List.of('a', 'e', 'i', 'o', 'u');
    private final static List<String> cipherList = new ArrayList<>();
    public static boolean isProperComposition(char[] cipher){

        int numberOfVowel = 0;
        int numberOfConsonant = 0;

        for (char alphabet : cipher) {
            if (VOWEL.contains(alphabet)) {
                numberOfVowel += 1;
            }
            else {
                numberOfConsonant += 1;
            }
        }
        return numberOfVowel > 0 && numberOfConsonant > 1;
    }

    public static boolean isProperOrder(char[] cipher, int depth){
        for (int i = 0; i < depth-1; i++) {
            if((int)cipher[i] >= (int)cipher[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void makeCipher(int depth, char[] alphabets, char[] ciphers, int maxDepth, boolean[] visited){
        if(depth==maxDepth){
            if(isProperComposition(ciphers) && isProperOrder(ciphers, depth)){
                cipherList.add(String.valueOf(ciphers));
            }
            return;
        }

        for (int i = 0; i < alphabets.length; i++) {
            if(!visited[i] && isProperOrder(ciphers, depth)){

                ciphers[depth] = alphabets[i];
                if(isProperOrder(ciphers, depth)){
                    visited[i] = true;
                    makeCipher(depth+1, alphabets, ciphers, maxDepth, visited);
                    visited[i] = false;
                }

            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] params = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .mapToInt(n -> n)
                .toArray();

        int cipherLength = params[0];
        int alphabetLength = params[1];

        char[] alphabets= br.readLine().replaceAll(" ", "").toCharArray();
        makeCipher(0, alphabets, new char[cipherLength], cipherLength, new boolean[alphabetLength]);
        cipherList.sort(Comparator.naturalOrder());
        System.out.println(String.join("\n",cipherList));

    }
}