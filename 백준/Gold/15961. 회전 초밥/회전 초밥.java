import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // bufferdReader 성능 비교 필요함
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfDishes = Integer.parseInt(st.nextToken());
        int numberOfSushi = Integer.parseInt(st.nextToken());
        int numberOfContinuous = Integer.parseInt(st.nextToken());
        int couponNumber = Integer.parseInt(st.nextToken());

        List<Integer> table = new ArrayList<>();

        for (int i = 0; i < numberOfDishes; i++) {
            int sushiNumber = Integer.parseInt(br.readLine());
            table.add(sushiNumber);
        }

        HashMap<Integer, Integer> dishKindMap = new HashMap<>();
        dishKindMap.put(couponNumber, 1);
        for (int i = 0; i < numberOfContinuous; i++) {
            dishKindMap.put(table.get(i), dishKindMap.getOrDefault(table.get(i), 0) + 1);
        }
        int numberOfKind = dishKindMap.size();
        for (int i = 1; i < numberOfDishes; i++) {
            int past = table.get(i-1);
            int end = (i + numberOfContinuous - 1) % numberOfDishes;
            int future = table.get(end);

            // 현재 슬라이딩 윈도우의 가장 처음 값 삭제

            // 가장 처음 값의 카운트가 1이면 이후 없어지므로 해시맵에서 삭제하고
            // 1보다 크다면 -1을 한 값을 다시 저장
            if (dishKindMap.get(past) == 1) {
                dishKindMap.remove(past);
            } else {
                int count = dishKindMap.get(past);
                dishKindMap.put(past, count - 1);
            }

            // 현재 슬라이딩 윈도우의 가장 마지막 값 저장
            dishKindMap.put(future, dishKindMap.getOrDefault(future, 0) + 1);

            // 스시 가짓수 비교
            numberOfKind = Math.max(numberOfKind, dishKindMap.size());
        }
        System.out.println(numberOfKind);
    }
}