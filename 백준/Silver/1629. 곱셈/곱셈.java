import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long number = scanner.nextInt();
        long coefficient = scanner.nextInt();
        long divisor = scanner.nextInt();

        // 분할 정복?
        long answer = divideAndConquer(number, coefficient, divisor);
        System.out.println(answer);
        scanner.close();
    }

    public static long divideAndConquer(long number, long coefficient, long divisor) {
        if (coefficient == 1) {
            return number % divisor;
        }
        long result = divideAndConquer(number, coefficient / 2, divisor);
        // 2씩 나누었기 때문에 곱해줌
        result = (result * result) % divisor;

        // 계수가 짝수라면 같은 거듭 제곱 꼴이므로 한 번 더 곱해줄 필요가 없음
        if (coefficient % 2 == 0) {
            return result;
        }
        // 계수가 홀수라면 거듭 제곱 + 1 만큼의 크기이므로 number를 한번 더 곱해준 뒤 모듈러 연산을 수행
        else {
            return (result * number) % divisor;
        }


    }
}