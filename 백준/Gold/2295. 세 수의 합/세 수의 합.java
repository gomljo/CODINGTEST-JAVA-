import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        Arrays.sort(numbers);
        Set<Integer> set = new HashSet<>();
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = numbers[i] + numbers[j];
                set.add(sum);
            }
        }
        boolean isFind = false;
        for (int i = numbers.length - 1; i >= 0; i--) {
            int number = numbers[i];
            for (int j = 0; j < n; j++) {
                if(set.contains(number-numbers[j])){
                    System.out.println(number);
                    isFind = true;
                    break;
                }
            }
            if(isFind){
                break;
            }
        }
    }

//    public static int binarySearch(int[] numbers, int sum) {
//        int start = 0;
//        int end = numbers.length;
//        int mid = 0;
//        int optimal = -1;
//        while (start < end) {
//            mid = (start + end) / 2;
//            if (sum + numbers[mid] > numbers[numbers.length-1]) {
//                end = mid - 1;
//            } else {
//                if()
//                optimal = Math.max(optimal, sum+numbers[mid]);
//                start = mid+1;
//            }
//        }
//        return -1;
//    }
}