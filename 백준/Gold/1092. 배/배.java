import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfCrain = Integer.parseInt(br.readLine());
        Integer[] crains = new Integer[numberOfCrain];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCrain; i++) {
            crains[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crains, Comparator.reverseOrder());
        int numberOfCargo = Integer.parseInt(br.readLine());
        List<Integer> cargos = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCargo; i++) {
            cargos.add(Integer.parseInt(st.nextToken()));
        }
        cargos.sort(Comparator.reverseOrder());

        if(cargos.get(0) > crains[0]){
            System.out.println("-1");
            return;
        }

        int timeForLoad = 0;

        while (!cargos.isEmpty()) {
            int crainNumber = 0;
            int cargoNumber = 0;

            while (crainNumber < numberOfCrain){
                if(cargoNumber==cargos.size()){
                    break;
                }
                if(crains[crainNumber] >= cargos.get(cargoNumber)){
                    // 크레인이 들 수 있다면 화물을 적재(리스트에서 삭제)한 다음
                    // 크레인의 작업물을 찾도록(크레인 번호를 증가) 화물을 찾는 크레인을 다음 크레인으로 변경
                    cargos.remove(cargoNumber);
                    crainNumber++;
                }
                else {
                    // 현재 크레인에 적합한 화물이 아니라면 다음 화물을 탐색
                    cargoNumber++;
                }
            }
            timeForLoad++;
        }
        System.out.println(timeForLoad);

        br.close();
    }
}