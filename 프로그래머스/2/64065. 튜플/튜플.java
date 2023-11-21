import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String s) {
        Subset subset = new Subset();
        subset.parsing(s);
        subset.makeTuple();
        return subset.getTuple().stream().mapToInt(i -> i).toArray();
    }
}
class Subset {

    private final List<Integer> tuple;
    private final List<Set<Integer>> subsets;
    private static final String OPEN_BRACKET = "{";
    private static final String CLOSE_BRACKET = "}";
    private static final String COMMA = ",";
    private static final String DELIMITER = "|";
    private static final String BLANK = "";
    public Subset(){
        this.tuple = new ArrayList<>();
        this.subsets = new ArrayList<>();
    }

    private String transform(String s){
        return s.replace(OPEN_BRACKET, BLANK)
                .replace(CLOSE_BRACKET+COMMA, DELIMITER)
                .replace(CLOSE_BRACKET+CLOSE_BRACKET, DELIMITER)
                .replace(COMMA, " ");
    }

    public void parsing(String s){
        String transformed = transform(s);
        String[] split = transformed.split("\\|");
        Arrays.sort(split, (set1, set2)-> set1.length() - set2.length());
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(split[0]));
        this.subsets.add(set);
        for (int i=1; i<split.length; i++) {
            subsets.add(Arrays.stream(split[i].split(" "))
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet()));
        }
    }

    public void makeTuple(){
        int value = this.subsets.get(0).iterator().next();
        this.tuple.add(value);
        for (int i=1; i < this.subsets.size(); i++){
            Set<Integer> current = new HashSet<>(this.subsets.get(i));
            Set<Integer> past = new HashSet<>(this.subsets.get(i-1));
            current.removeAll(past);
            this.tuple.addAll(current);
        }

    }

    public List<Integer> getTuple() {
        return tuple;
    }
}
