import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        TeamAbilityAnalyzer teamAbilityAnalyzer = new TeamAbilityAnalyzer(n, table);
        int k = n / 2;
        teamAbilityAnalyzer.make(1, 0, k, new boolean[n + 1]);
        int answer = teamAbilityAnalyzer.getMinAbilityDiff();
        System.out.println(answer);
        br.close();


    }
}

class TeamAbilityAnalyzer {
    private int n;
    private int minAbilityDiff;
    private int[][] abilityTable;

    public TeamAbilityAnalyzer(int n, int[][] abilityTable) {
        this.n = n;
        this.abilityTable = abilityTable;
        this.minAbilityDiff = Integer.MAX_VALUE;
    }

    public void make(int start, int depth, int k, boolean[] visited) {
        if (depth == k) {
            analyze(visited);
            return;
        }

        for (int i = start; i <= this.n; i++) {
            visited[i] = true;
            make(i + 1, depth + 1, k, visited);
            visited[i] = false;
        }
    }

    private void analyze(boolean[] visited) {
        int startTeamIndex = 0;
        int linkTeamIndex = 0;
        for (int i = 1; i < this.n; i++) {
            for (int j = i + 1; j <= this.n; j++) {
                if (visited[i] && visited[j]) {
                    startTeamIndex += this.abilityTable[i - 1][j - 1];
                    startTeamIndex += this.abilityTable[j - 1][i - 1];
                } else if (!visited[i] && !visited[j]) {
                    linkTeamIndex += this.abilityTable[i - 1][j - 1];
                    linkTeamIndex += this.abilityTable[j - 1][i - 1];
                }
            }
        }
        this.minAbilityDiff = Math.min(this.minAbilityDiff, Math.abs(startTeamIndex - linkTeamIndex));
    }

    public int getMinAbilityDiff() {
        return minAbilityDiff;
    }
}