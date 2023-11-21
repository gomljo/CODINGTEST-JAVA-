import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    private static final List<List<Integer>> sequences = new ArrayList<>();
    public static void makeCombination(int depth, int maxDepth, int[] temp, boolean[] visited){

        if(depth==maxDepth){
            sequences.add(Arrays.stream(temp).boxed().collect(Collectors.toList()));
            return;
        }

        for (int i = 0; i < maxDepth; i++) {
            if(!visited[i]){
                visited[i] = true;
                temp[depth] = i;
                makeCombination(depth+1, maxDepth, temp, visited);
                visited[i] = false;
            }

        }
    }

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        List<Dungeon> dungeonList = new ArrayList<>();
        for (int i = 0; i < dungeons.length; i++) {
            dungeonList.add(new Dungeon(i, dungeons[i][0], dungeons[i][1]));
        }
        makeCombination(0, dungeons.length, new int[dungeons.length], new boolean[dungeons.length]);
        for (List<Integer> sequence: sequences) {
            Player player = new Player(k, 0);

            for (int order: sequence) {
                Dungeon dungeon = dungeonList.get(order);
                if(player.getCurrent() < dungeon.getMinimum()){
                    break;
                }
                else{
                    player.minusHealth(dungeon.getCost());
                    player.plusCount();
                }
            }
            answer = Math.max(answer, player.getNumberOfVisitedDungeons());
        }
        return answer;
    }
}

class Dungeon{

    private final int idx;
    private final int minimum;
    private final int cost;

    public Dungeon(int idx, int minimum, int cost) {
        this.idx = idx;
        this.minimum = minimum;
        this.cost = cost;
    }


    public int getIdx() {
        return idx;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getCost() {
        return cost;
    }
}

class Player {
    private int current;
    private int numberOfVisitedDungeons;

    public Player(int current, int numberOfVisitedDungeons) {
        this.current = current;
        this.numberOfVisitedDungeons = numberOfVisitedDungeons;
    }

    public int getCurrent() {
        return current;
    }

    public int getNumberOfVisitedDungeons() {
        return numberOfVisitedDungeons;
    }

    public void plusCount(){
        this.numberOfVisitedDungeons++;
    }
    public void minusHealth(int lost){
        this.current -= lost;
    }
}