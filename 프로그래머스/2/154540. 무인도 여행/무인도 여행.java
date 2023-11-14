import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public static void bfs(char[][] maps, int row, int col, List<Integer> foods){

        if(row < 0 || row >= maps.length || col < 0 || col >=maps[0].length || maps[row][col] == 'X'){
            return;
        }
        foods.add(Integer.parseInt(String.valueOf(maps[row][col])));
        maps[row][col] = 'X';

        bfs(maps, row+1, col, foods);
        bfs(maps, row-1, col, foods);
        bfs(maps, row, col+1, foods);
        bfs(maps, row, col-1, foods);
    }

    public static List<Integer> searchFood(String[] maps){
        List<Integer> totalFood = new ArrayList<>();
        char[][] map = new char[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[0].length; j++) {
                List<Integer> foods = new ArrayList<>();
                bfs(map, i, j, foods);
                if(!foods.isEmpty()) {
                    totalFood.add(foods.stream().reduce(Integer::sum).get());
                }
            }
        }
        return totalFood.stream().sorted().collect(Collectors.toList());
    }

    public int[] solution(String[] maps) {

        List<Integer> answer = searchFood(maps);
        if(answer.isEmpty()){
            return new int[]{-1};
        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}