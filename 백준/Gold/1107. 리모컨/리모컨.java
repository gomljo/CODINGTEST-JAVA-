import java.util.Scanner;

public class Main {
    private static final int MAX_CHANNEL = 999999;
    private static final int NUMBER_OF_BUTTON = 10;
    private static boolean[] brokenButton;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int targetChannel = sc.nextInt();
        int startChannel = 100;
        int numberOfBrokenButton = sc.nextInt();
        brokenButton = new boolean[NUMBER_OF_BUTTON];

        for (int i = 0; i < numberOfBrokenButton; i++) {
            int brokenButton = sc.nextInt();
            Main.brokenButton[brokenButton] = true;
        }
        int minimumCount = Math.abs(targetChannel - startChannel);
        for (int channel = 0; channel <= MAX_CHANNEL; channel++) {
            String currentChannel = String.valueOf(channel);
            int channelLength = currentChannel.length();
            boolean existsAnyBrokenButton = false;
            for (int j = 0; j < channelLength; j++) {
                if(brokenButton[currentChannel.charAt(j) - '0']){
                    existsAnyBrokenButton = true;
                    break;
                }
            }
            if(!existsAnyBrokenButton){
                int currentCount = channelLength + Math.abs(channel - targetChannel);
                minimumCount = Math.min(minimumCount, currentCount);
            }
        }
        System.out.println(minimumCount);
    }
}