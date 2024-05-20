import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfDays = scanner.nextInt();
        int numberOfRiceCake = scanner.nextInt();
        Coefficient[] coefficients = new Coefficient[numberOfDays + 1];
        coefficients[1] = new Coefficient(1, 0);
        coefficients[2] = new Coefficient(0, 1);

        for (int i = 3; i <= numberOfDays; i++) {
            Coefficient dayBeforeYesterday = coefficients[i - 2];
            Coefficient yesterday = coefficients[i - 1];
            coefficients[i] = new Coefficient(dayBeforeYesterday.getA1() + yesterday.getA1(), dayBeforeYesterday.getA2() + yesterday.getA2());
        }

        int a1 = coefficients[numberOfDays].getA1();
        int a2 = coefficients[numberOfDays].getA2();
        int quotient = numberOfRiceCake / a2;
        int candidate1 = 0;
        int candidate2 = 0;
        for (int i = 1; i < quotient; i++) {
            int res = numberOfRiceCake - (a2 * i);

            if (res % a1 == 0 && calculate(Math.min(i, res/ a1), Math.max(i, res/ a1), numberOfDays) == numberOfRiceCake) {
                candidate1 = i;
                candidate2 = res / a1;
            }
        }
        int a = Math.min(candidate1, candidate2);
        int b = Math.max(candidate1, candidate2);
        System.out.println(a);
        System.out.println(b);
        scanner.close();
    }

    public static int calculate(int a, int b, int days) {
        int c = 0;
        for (int i = 3; i <= days; i++) {
            c = b + a;
            a = b;
            b = c;
        }
        return c;
    }
}

class Coefficient {
    private int a1;
    private int a2;

    public Coefficient(int a1, int a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public int getA1() {
        return a1;
    }

    public int getA2() {
        return a2;
    }
}