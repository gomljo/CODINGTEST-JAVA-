import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] isShortcut = new boolean[26];
        List<String> options = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String option = br.readLine();
            String[] words = option.split(" ");
            boolean isNotFind = true;
            int length = 0;
            for (String word : words) {
                word = word.toLowerCase();
                char firstAlphabet = word.charAt(0);
                if (!isShortcut[firstAlphabet - 'a']) {
                    ShortCut shortCut = new ShortCut(i, option, length);
                    options.add(shortCut.getShortCut());
                    isShortcut[firstAlphabet - 'a'] = true;
                    isNotFind = false;
                    break;
                } else {
                    length += word.length() + 1;
                }
            }
            if (!isNotFind) {
                continue;
            }
            boolean isNoShortCut = true;
            String lowerOption = option.toLowerCase();
            for (int k = 0; k < lowerOption.length(); k++) {
                if (lowerOption.charAt(k) == ' ') {
                    continue;
                }
                int index = lowerOption.charAt(k) - 'a';
                if (!isShortcut[index]) {
                    ShortCut shortCut = new ShortCut(i, option, k);
                    options.add(shortCut.getShortCut());
                    isNoShortCut = false;
                    isShortcut[index] = true;
                    break;
                }
            }

            if (isNoShortCut) {
                options.add(option);
            }
        }
        for (String option:options){
            System.out.println(option);
        }
        br.close();
    }
}

class ShortCut {
    private final int index;
    private final String option;
    private final int shortcutIndex;

    public ShortCut(int index, String option, int shortcutIndex) {
        this.index = index;
        this.option = option;
        this.shortcutIndex = shortcutIndex;
    }

    public int getIndex() {
        return index;
    }

    public String getShortCut() {
        String frontal = option.substring(0, shortcutIndex);
        String rear = option.substring(Math.min(shortcutIndex + 1, option.length()));
        String shortcut = String.valueOf(option.charAt(shortcutIndex));
        return frontal + "[" + shortcut + "]" + rear;
    }

    @Override
    public String toString() {
        return "ShortCut{" +
                "index=" + index +
                ", option='" + option + '\'' +
                ", shortcutIndex=" + shortcutIndex +
                '}';
    }
}