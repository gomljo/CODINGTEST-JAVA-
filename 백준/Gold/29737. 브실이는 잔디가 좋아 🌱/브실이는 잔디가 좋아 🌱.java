import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Streak> list = new ArrayList<>();
        while (n-- > 0) {
            String name = sc.next();
            String[][] arr = new String[7][];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.next().split("");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < 7; j++) {
                    sb.append(arr[j][i]);
                }
            }
            int size = 0;
            int freeze = 0;
            int freezeBefore = 0;
            int startDay = -1;
            int fail = 0;
            int idx = 0;
            Streak streak = new Streak();
            for (String s : sb.toString().split("")) {
                if (s.equals("O")) {
                    if (startDay == -1) {
                        startDay = idx;
                    }
                    size++;
                    freezeBefore = 0;
                } else if (size != 0 && s.equals("F")) {
                    freeze++;
                    freezeBefore++;
                } else if (s.equals("X")) {
                    streak.change(size, freeze - freezeBefore, startDay);
                    fail++;
                    freeze = 0;
                    size = 0;
                    startDay = -1;
                }
                idx++;
            }
            streak.change(size, freeze - freezeBefore, startDay);
            streak.name = name;
            streak.fail = fail;
            list.add(streak);
        }
        Collections.sort(list);
        int order = 1;
        Streak before = null;
        for (Streak each : list) {
            if (Objects.nonNull(before) && !each.equals(before)) {
                order++;
            }
            before = each;
            System.out.printf("%d. %s\n", order, each.name);
        }
    }
    static class Streak implements Comparable<Streak> {
        String name;
        int size;
        int freeze;
        int startDay;
        int fail;
        public void change(int size, int freeze, int startDay) {
            if (this.size < size) {
                this.size = size;
                this.freeze = freeze;
                this.startDay = startDay;
            } else if (this.size == size) {
                if (this.freeze > freeze) {
                    this.freeze = freeze;
                    this.startDay = startDay;
                }
            }
        }
        @Override
        public String toString() {
            return "Streak{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    ", freeze=" + freeze +
                    ", startDay=" + startDay +
                    ", fail=" + fail +
                    '}';
        }
        @Override
        public int compareTo(Streak o) {
            if (this.size != o.size) {
                return o.size - this.size;
            }
            if (this.freeze != o.freeze) {
                return this.freeze - o.freeze;
            }
            if (this.startDay != o.startDay) {
                return this.startDay - o.startDay;
            }
            if (this.fail != o.fail) {
                return this.fail - o.fail;
            }
            return this.name.compareTo(o.name);
        }
        public boolean equals(Streak o) {
            return this.size == o.size
                    && this.freeze == o.freeze
                    && this.startDay == o.startDay
                    && this.fail == o.fail;
        }
    }
}