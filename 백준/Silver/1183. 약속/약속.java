import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfWizards = Integer.parseInt(br.readLine());
        List<Wizard> wizardList = new ArrayList<>();
        for (int i = 0; i < numberOfWizards; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int appointmentTime = Integer.parseInt(st.nextToken());
            int arrivalTime = Integer.parseInt(st.nextToken());
            wizardList.add(new Wizard(appointmentTime, arrivalTime));
        }
        TimeMediator mediator = new TimeMediator(wizardList);
        mediator.sortByTimeDifference();
        long answer = mediator.calculateWaitingTime();
        System.out.println(answer);
        br.close();
    }
}

class Wizard {
    private final long appointmentTime;
    private final long arrivalTime;

    public Wizard(long appointmentTime, long arrivalTime) {
        this.appointmentTime = appointmentTime;
        this.arrivalTime = arrivalTime;
    }

    public long getAppointmentTime() {
        return appointmentTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getDiff() {
        return this.arrivalTime - this.appointmentTime;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "appointmentTime=" + appointmentTime +
                ", arrivalTime=" + arrivalTime +
                ", diff= " + (this.appointmentTime - this.arrivalTime) +
                '}';
    }
}

class TimeMediator {
    private final List<Wizard> wizardList;

    public TimeMediator(List<Wizard> wizardList) {
        this.wizardList = wizardList;
    }

    public void sortByTimeDifference() {
        wizardList.sort(Comparator.comparing(Wizard::getDiff));
    }

    public long calculateWaitingTime() {
        if(this.wizardList.size()%2==1){
            return 1;
        }
        int half = this.wizardList.size() / 2;
        return this.wizardList.get(half).getDiff() - this.wizardList.get(half-1).getDiff() +1;
    }

}