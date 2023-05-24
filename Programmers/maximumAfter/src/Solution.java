import java.util.*;

class Solution {

    public static void dfs(char[][] maps, int x, int y, List<Integer> foods){

        // 만약 육지가 아니라면 종료
        if(x < 0 || x >= maps.length || y < 0 || y >= maps[0].length || maps[x][y]=='X'){
            return;
        }

        // 육지라면 식량을 추가
        foods.add(Integer.parseInt(Character.toString(maps[x][y])));

        // 다시 돌아오면 안되므로 방문한 육지는 'X'로 변환
        maps[x][y] = 'X';

        // 동,서,남,북을 탐색
        dfs(maps, x+1, y, foods);
        dfs(maps, x-1, y, foods);
        dfs(maps, x, y+1, foods);
        dfs(maps, x, y-1, foods);
    }

    public static List<Integer> getFoods(char[][] maps){
        // 무인도들의 식량 보유 상태를 저장하기 위한 리스트 선언
        List<Integer> totalFoods = new ArrayList<>();

        // 모든 좌표를 순회해야하고 좌표는 2차원이므로 2중 반복문으로 탐색
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length; j++){
                // 만약 육지인 경우만 탐색
                if(maps[i][j]!='X'){
                    // 무인도의 식량들을 모으기 위한 리스트 선언
                    List<Integer> foods = new ArrayList<>();
                    // 무인도 탐색 시작
                    dfs(maps, i, j, foods);
                    // 탐색 후의 무인도 식량을 모두 더해서 상태 리스트에 저장
                    totalFoods.add(foods.stream().reduce(Integer::sum).get());
                }
            }

        }
        return totalFoods;
    }

    public int[] solution(String[] maps) {
        int[] answer = {};
        char[][] map = new char[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            map[i] = maps[i].toCharArray();
        }
        List<Integer> foods = getFoods(map);

        // 만약 식량이 하나도 없다면 -1을 추가하고 int 배열로 변환 후 리턴
        if(foods.isEmpty()){
            foods.add(-1);
            return foods.stream().mapToInt(i->i).toArray();
        }

        // 식량이 있다면 오름차순으로 정렬 후 int 배열로 변환하여 리턴
        return foods.stream().sorted(Comparator.naturalOrder()).mapToInt(i->i).toArray();
    }
}