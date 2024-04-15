import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Employee[] employees;
    private static int n;
    private static boolean[] visited;
    private static int[] total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] supervisor = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        employees = new Employee[n + 1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            employees[i] = new Employee(i);
        }
        for (int i = 0; i < n; i++) {
            int s = supervisor[i];
            if (s == -1) {
                s = 0;
            }
            Employee employee = employees[s];
            Employee subordinate = employees[i + 1];
            employee.addSubordinate(subordinate);
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            int how = Integer.parseInt(st.nextToken());
            employees[who].addScore(how);
        }
        total = new int[n+1];
        for (int i = 1; i <=n; i++) {
            if(!visited[i]){
                dfs(i);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            stringBuilder.append(total[i] + " ");
        }
        System.out.println(stringBuilder);
        br.close();
    }
    private static void dfs(int index)
    {
        visited[index] = true;
        total[index] += employees[index].getScore();
        for (Employee subordinate: employees[index].getSubordinates())
        {
            if (!visited[subordinate.getIndex()])
            {
                total[subordinate.getIndex()] += total[index];
                dfs(subordinate.getIndex());
            }
        }
    }
    public static void doCompliment(int who, int how) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(who);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            Employee employee = employees[index];
            for (Employee subordinate : employee.getSubordinates()) {
                subordinate.addScore(how);
                queue.offer(subordinate.getIndex());
            }
        }
    }
}

class Employee {
    private int index;
    private int score;

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    private List<Employee> subordinates;

    public Employee(int supervisorIndex) {
        this.index = supervisorIndex;
        this.score = 0;
        this.subordinates = new ArrayList<>();
    }

    public void changeIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return this.score;
    }

    public void addSubordinate(Employee employee) {
        this.subordinates.add(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "index=" + index +
                ", score=" + score +
                '}';
    }
}