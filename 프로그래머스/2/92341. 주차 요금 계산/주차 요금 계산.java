import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static void addTimeWithType(HashMap<String, ParkingTime> parkingRecords, RecordParser recordParser, ParkingTime parkingTime){

        if (recordParser.getType().equals("IN")) {
            parkingTime.addInTime(recordParser.getTime());
            parkingRecords.put(recordParser.getCarNumber(), parkingTime);
        }
        else {
            parkingTime.addOutTime(recordParser.getTime());
            parkingRecords.put(recordParser.getCarNumber(), parkingTime);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        int[] answer;

        HashMap<String, ParkingTime> parkingRecords = new HashMap<>();

        for (String record : records) {
            RecordParser recordParser = new RecordParser(record);
            recordParser.parseRecord();
            ParkingTime parkingTime;
            if (!parkingRecords.containsKey(recordParser.getCarNumber())) {
                parkingTime = new ParkingTime(recordParser.getCarNumber(), new ArrayList<>(), new ArrayList<>(), 0);
            } else {
                parkingTime = parkingRecords.get(recordParser.getCarNumber());
            }
            addTimeWithType(parkingRecords, recordParser, parkingTime);
        }

        List<String> keys = new ArrayList<>(parkingRecords.keySet());
        answer = new int[keys.size()];
        HashMap<String, Integer> result = new HashMap<>();
        ParkingFeeCalculator calculator = new ParkingFeeCalculator(fees);
        for (String key : keys) {
            ParkingTime parkingTime = parkingRecords.get(key);
            parkingTime.calculateTotalDuration();
            int fee = calculator.calculate(parkingTime.getDuration());
            result.put(key, fee);
        }

        // 마지막에 key값으로 정렬

        List<String> resultKeys = new ArrayList<>(result.keySet());
        resultKeys.sort(Comparator.naturalOrder());
        for (int i = 0; i < resultKeys.size(); i++) {
            answer[i] = result.get(resultKeys.get(i));
        }
        return answer;
    }
}

class RecordParser {

    private Integer time;
    private String type;
    private String carNumber;
    private final String record;

    public RecordParser(String record) {
        this.record = record;
    }

    public Integer getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void parseRecord() {
        String[] splitedRecord = this.record.split(" ");
        String[] times = splitedRecord[0].split(":");
        this.carNumber = splitedRecord[1];
        this.time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        this.type = splitedRecord[2];

    }

}

class ParkingTime {
    private static final int MAX_TIME = 23 * 60 + 59;
    private final String carNumber;
    private final List<Integer> in;
    private final List<Integer> out;
    private int duration;


    public ParkingTime(String carNumber, List<Integer> in, List<Integer> out, int duration) {
        this.carNumber = carNumber;
        this.in = in;
        this.out = out;
        this.duration = duration;
    }

    public int getDuration(){
        return this.duration;
    }

    public void addInTime(int time) {
        this.in.add(time);
    }

    public void addOutTime(int time) {
        this.out.add(time);
    }

    public void calculateTotalDuration() {
        int maxLength = this.in.size();
        boolean isLongParking = false;
        if(this.in.size() > this.out.size()) {
            maxLength = this.out.size();
            isLongParking = true;
        }
        for (int i = 0; i < maxLength; i++) {
            this.duration += this.out.get(i) - this.in.get(i);
        }

        if(isLongParking){
            this.duration += MAX_TIME - this.in.get(this.in.size() - 1);
        }
    }


    @Override
    public String toString() {
        return "ParkingFee{" +
                "carNumber='" + carNumber + '\'' +
                ", in=" + in +
                ", out=" + out +
                '}';
    }
}

class ParkingFeeCalculator{

    int basicTime;
    int basicFee;
    int unitTime;
    int unitFee;

    public ParkingFeeCalculator(int[] fees) {
        this.basicTime = fees[0];
        this.basicFee = fees[1];
        this.unitTime = fees[2];
        this.unitFee = fees[3];
    }
    private boolean isOverBasicTime(int duration){
        return duration > this.basicTime;
    }

    public int calculate(int duration) {
        int fee = this.basicFee;
        if(isOverBasicTime(duration)){
            duration -= this.basicTime;
            int unit = duration / this.unitTime;
            if(duration % this.unitTime != 0){
                unit += 1;
            }
            fee += unit * this.unitFee;
        }
        return fee;
    }

}