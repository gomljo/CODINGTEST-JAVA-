import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] average = new int[n];
        for (int i = 0; i < n; i++) {
            String avg = scanner.next();
            average[i] = Integer.parseInt(avg.replace(".",""));
        }

        Arrays.sort(average);

        long numberOfPeople = 1L;
        while (true) {

            int index = 0;
            for (long i = 0; i <= numberOfPeople*10; i++) {
                if(index >= average.length){
                    break;
                }
                long avg = i*1000 / numberOfPeople;
                if(average[index]== avg){
                    while (index+1< average.length && average[index+1]==avg){
                        index++;
                    }
                    index++;
                }
            }
            if(index== average.length){
                break;
            }
            numberOfPeople++;
        }
        System.out.println(numberOfPeople);
    }
}