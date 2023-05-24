import java.util.Scanner;

public class Main {

    public static boolean checkAllMatch(int start, int end, String[] strArr){

        while (start <= end){

            if(!strArr[end--].equals(strArr[start++])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int answer = 0;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strArr = str.split("");
        scanner.close();
        int start = 0;
        int end = strArr.length-1;
        while (start<=end){
            if(!strArr[end].equals(strArr[start])){
                start++;
            }
            else {
                if(checkAllMatch(start, end, strArr)){
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(str, 0, start);
                    answer = sb.length();
                    break;
                }
                else {
                    start++;
                }
            }

        }

        System.out.println(answer);

    }

}