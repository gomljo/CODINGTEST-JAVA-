import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    private static final int[][] COST = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;


        int totalPicks = picks[0] + picks[1] + picks[2];
        List<Mine> mineList = new ArrayList<>();
        for (int i = 0; i < minerals.length; i += 5) {
            if (totalPicks <= 0) {
                break;
            }
            int diamondCost = 0;
            int ironCost = 0;
            int rockCost = 0;
            for (int j = i; j < i + 5; j++) {
                if (j >= minerals.length) {
                    break;
                }
                int index;
                if (minerals[j].equals("diamond")) {
                    index = 0;
                } else if (minerals[j].equals("iron")) {
                    index = 1;
                } else {
                    index = 2;
                }

                diamondCost += COST[0][index];
                ironCost += COST[1][index];
                rockCost += COST[2][index];
            }
            mineList.add(new Mine(diamondCost, ironCost, rockCost));
            totalPicks -= 1;
        }

        mineList.sort(new Comparator<Mine>() {
            @Override
            public int compare(Mine o1, Mine o2) {
                return o2.getRock() - o1.getRock();
            }
        });

        for (Mine mine : mineList) {
            if(picks[0] > 0){
                answer += mine.getDiamond();
                picks[0] -= 1;
            } else if (picks[1] > 0) {
                answer += mine.getIron();
                picks[1] -= 1;
            }
            else {
                answer += mine.getRock();
                picks[2] -= 1;
            }
        }
        return answer;
    }
}

class Mine {

    private final int diamond;
    private final int iron;
    private final int rock;

    public Mine(int diamond, int iron, int rock) {
        this.diamond = diamond;
        this.iron = iron;
        this.rock = rock;
    }

    public int getDiamond() {
        return diamond;
    }

    public int getIron() {
        return iron;
    }

    public int getRock() {
        return rock;
    }

}