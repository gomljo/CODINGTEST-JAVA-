

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int[] dRow = new int[]{1, -1, 0, 0};
    private static final int[] dCol = new int[]{0, 0, 1, -1};
    private static final int maxDepth = 2;
    public static void bfs(int depth, String[][] room, int[] pos, List<Boolean> isCorrect, boolean[][] visited) {

        if(depth==maxDepth){
            return;
        }

        for (int i = 0; i < dRow.length; i++) {
            int nextRow = pos[0] + dRow[i];
            int nextCol = pos[1] + dCol[i];
            if((0<=nextRow &&nextRow < room.length) && (0<=nextCol && nextCol < room.length) && !visited[nextRow][nextCol]){
                visited[nextRow][nextCol] = true;
                if(room[nextRow][nextCol].equals("O")){
                    bfs(depth+1, room, new int[]{nextRow, nextCol}, isCorrect, visited);
                }
                else if (room[nextRow][nextCol].equals("P")){
                    isCorrect.add(false);
                    return;
                }
            }
        }
    }

   public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            String[][] room = makeMap(places[i]);
            List<int[]> participants = getParticipants(room);
            if (participants.isEmpty()) {
                answer[i] = 1;
                continue;
            }
            for (int[] participant : participants) {
                List<Boolean> isCorrect = new ArrayList<>();
                boolean[][] visited = new boolean[5][5];
                visited[participant[0]][participant[1]] = true;
                bfs(0, room, participant, isCorrect, visited);
                if (isCorrect.contains(false)) {
                    answer[i] = 0;
                    break;
                }
            }

        }
        return answer;
    }

    public static String[][] makeMap(String[] waitingRoom) {
        String[][] room = new String[5][5];
        for (int i = 0; i < 5; i++) {
            String[] row = waitingRoom[i].split("");
            for (int j = 0; j < 5; j++) {
                room[i][j] = row[j];
            }
        }
        return room;
    }

    public static List<int[]> getParticipants(String[][] waitingRoom) {
        List<int[]> coordinates = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (waitingRoom[i][j].equals("P")) {
                    coordinates.add(new int[]{i, j});
                }
            }
        }
        return coordinates;
    }
}