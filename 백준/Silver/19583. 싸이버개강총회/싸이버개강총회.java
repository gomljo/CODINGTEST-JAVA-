import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = convertTime(st.nextToken());
        int end = convertTime(st.nextToken());
        int streamingEnd = convertTime(st.nextToken());
        HashMap<String, Attendance> attendanceList = new HashMap<>();
        List<String> alreadyAttend = new ArrayList<>();
        String str = "";
        int answer = 0;
        while ((str = br.readLine()) != null) {
            if (str.equals("")) {
                break;
            }
            String[] timeAndName = str.split(" ");
            int time = convertTime(timeAndName[0]);
            String name = timeAndName[1];

            if (alreadyAttend.contains(name)) {
                continue;
            }
            Attendance attendance = new Attendance(false, false);
            if (attendanceList.containsKey(name)) {
               attendance = attendanceList.get(name);
            }
            if (time <= start) {
                attendance.checkBeforeStart();
            }
            if (end <= time && time <= streamingEnd) {
                attendance.checkBeforeEnd();
            }
            if (attendance.isAttend()) {
                alreadyAttend.add(name);
                answer++;
            }
            attendanceList.put(name, attendance);
        }
        System.out.println(answer);
        br.close();
    }

    public static int convertTime(String time) {
        String[] hourAndMinute = time.split(":");
        return Integer.parseInt(hourAndMinute[0]) * 60 + Integer.parseInt(hourAndMinute[1]);
    }
}

class Attendance {
    private boolean isChatBeforeStart;
    private boolean isChatBeforeStreamingEnd;

    public Attendance(boolean isChatBeforeStart, boolean isChatBeforeStreamingEnd) {
        this.isChatBeforeStart = isChatBeforeStart;
        this.isChatBeforeStreamingEnd = isChatBeforeStreamingEnd;
    }

    public void checkBeforeStart() {
        this.isChatBeforeStart = true;
    }

    public void checkBeforeEnd() {
        this.isChatBeforeStreamingEnd = true;
    }

    public boolean isAttend() {
        return this.isChatBeforeStart && this.isChatBeforeStreamingEnd;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "isChatBeforeStart=" + isChatBeforeStart +
                ", isChatBeforeStreamingEnd=" + isChatBeforeStreamingEnd +
                '}';
    }
}