import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_IPV6_ADDRESS_BLOCK = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ipv6Address = br.readLine();
        String recoveredAddress = "";
        if (ipv6Address.contains("::")) {
            String[] doubleColonSplit = ipv6Address.split("::");
            // 맨 앞 또는 맨 뒤가 모두 0으로 연속된 주소
            if (ipv6Address.startsWith("::")) {
                String[] frontOrRear = doubleColonSplit[1].split(":");
                int numberOfContinuous = NUMBER_OF_IPV6_ADDRESS_BLOCK - frontOrRear.length;
                String rearAddress = recover(frontOrRear);
                String zeroAddress = makeAllZeroBlock(numberOfContinuous);
                recoveredAddress = zeroAddress + ":" + rearAddress;
            } else if (ipv6Address.endsWith("::")) {
                String[] frontOrRear = doubleColonSplit[0].split(":");
                int numberOfContinuous = NUMBER_OF_IPV6_ADDRESS_BLOCK - frontOrRear.length;
                String frontalAddress = recover(frontOrRear);
                String zeroAddress = makeAllZeroBlock(numberOfContinuous);
                recoveredAddress = frontalAddress + ":" + zeroAddress;
            } else {
                String[] front = doubleColonSplit[0].split(":");
                String[] rear = doubleColonSplit[1].split(":");
                int numberOfContinuous = NUMBER_OF_IPV6_ADDRESS_BLOCK - (front.length + rear.length);
                String frontRecoveredAddress = recover(front);
                String rearRecoveredAddress = recover(rear);
                String allZeroAddress = makeAllZeroBlock(numberOfContinuous);
                recoveredAddress = frontRecoveredAddress + ":" + allZeroAddress + ":" + rearRecoveredAddress;
            }
            System.out.println(recoveredAddress);
        }else {
            String[] eachAddress = ipv6Address.split(":");
            recoveredAddress = recover(eachAddress);

            System.out.println(recoveredAddress);
        }
        br.close();
    }

    public static String recover(String[] address) {
        List<String> recovered = new ArrayList<>();

        for (String s : address) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4 - s.length(); i++) {
                sb.append("0");
            }
            sb.append(s);
            recovered.add(sb.toString());
        }
        return String.join(":", recovered);
    }

    public static String makeAllZeroBlock(int repeat) {
        String allZero = "0000";
        List<String> address = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            address.add(allZero);
        }
        return String.join(":", address);
    }
}