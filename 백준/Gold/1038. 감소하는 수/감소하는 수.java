import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static boolean isProperRange(String number){
        return number.length() <= 10;
    }

    public static String findDecreaseNumber(int order) {
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i <= 9; i++) {
            queue.add(String.valueOf(i));
        }
        int cnt = 9;
        String wantNumber;
        while (!queue.isEmpty()) {

            String number = queue.poll();
            if(!isProperRange(number)){
                break;
            }
            for (int i = 0; i <= 9; i++) {
                if(Integer.parseInt(number.substring(number.length()-1)) > i){
                    queue.add(number+i);
                    wantNumber = number+i;
                    cnt++;
                    if(cnt==order){
                        return wantNumber;
                    }
                }
            }

        }
        return "-1";
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int order = sc.nextInt();
        if (order <= 9) {
            System.out.println(order);
        } else {
            String number = findDecreaseNumber(order);
            System.out.println(number);
        }

    }
}