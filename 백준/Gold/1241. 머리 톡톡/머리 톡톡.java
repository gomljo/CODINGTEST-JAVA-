import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            count.put(numbers[i], count.getOrDefault(numbers[i], 0) + 1);
        }
        HashMap<Integer, Integer> result = new HashMap<>();
        List<Integer> keys = new LinkedList<>(count.keySet());
        for (int key : keys) {
//            if (key == 1) {
//                result.put(key, 0);
//
//            }
            result.put(key,0);
            Set<Integer> lsd = new HashSet<>();
            for (int i = 1; i < Math.sqrt(key) + 1; i++) {
                if (key % i == 0) {
                    lsd.add(i);
                    lsd.add(key / i);
                }
            }
            for (int i : lsd) {
                if (count.containsKey(i)) {
                    if(i==key){
                        result.put(key, result.getOrDefault(key, 0) + count.get(i)-1);
                    }
                    else {
                        result.put(key, result.getOrDefault(key, 0) + count.get(i));
                    }
                    
                }
            }
//            int max = keys.get(keys.size() - 1);
//            int start = 2;
//            while (key * start <= max){
//                if(count.containsKey(key*start)){
//                    result.put(key, result.getOrDefault(key,0)+count.get(key*start));
//                }
//                start++;
//            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result.get(numbers[i])).append("\n");
        }
        System.out.println(sb);

        scanner.close();
    }
}