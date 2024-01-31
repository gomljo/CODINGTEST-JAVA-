import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfCrane = Integer.parseInt(br.readLine());
        Integer[] cranes = new Integer[numberOfCrane];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCrane; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int numberOfCargo = Integer.parseInt(br.readLine());
        List<Integer> cargo = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCargo; i++) {
            cargo.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(cranes, Collections.reverseOrder());
        cargo.sort(Comparator.reverseOrder());
        if(cranes[0] < cargo.get(0)){
            System.out.println("-1");
            return;
        }
        // 크레인으로 실을 수 없는 경우 => 크레인 갯수만큼
        int answer = 0;
        while (!cargo.isEmpty()){
            int index = 0;
            for (int i = 0; i < numberOfCrane;) {
                if(index==cargo.size()){
                    break;
                }
                if(cargo.get(index) <= cranes[i]){
                    cargo.remove(index);
                    i++;
                }
                else {
                    index++;
                }
            }
            answer++;
        }
        System.out.println(answer);

    }

    public static boolean isPossibleToShipping(Integer[] cargo, boolean[] shipped, int craneLimit) {
        for (int i = 0; i < cargo.length; i++) {
            if (!shipped[i] && cargo[i] <= craneLimit) {
                shipped[i] = true;
                return true;
            }
        }
        return false;
    }

    public static boolean isFinished(boolean[] shipped) {
        for (boolean shipState : shipped) {
            if (!shipState) {
                return false;
            }
        }
        return true;
    }
}