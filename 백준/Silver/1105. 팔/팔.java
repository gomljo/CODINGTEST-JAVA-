import java.util.Scanner;

public class Main {
    // 자릿수 비교?
    // start와 end의 자릿수를 비교한다.
    // 같은 경우만 확인한다
    // why? 자릿수가 다르면 모두 8이 아니게 세팅 가능

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start = sc.next();
        String end = sc.next();
        int count = 0;
        if (start.length() == end.length()) {
            for (int i = 0; i < start.length(); i++) {
                if(start.charAt(i)!=end.charAt(i)){
                    break;
                }
                if(end.charAt(i)=='8'){
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}