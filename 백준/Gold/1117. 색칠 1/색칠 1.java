import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long w = scanner.nextInt();
        long h = scanner.nextInt();
        long f = scanner.nextInt();
        long c = scanner.nextInt();
        long x1 = scanner.nextInt();
        long y1 = scanner.nextInt();
        long x2 = scanner.nextInt();
        long y2 = scanner.nextInt();

        long newW = w - f;
        long area = w*h;

        long leftPaper = Math.min(newW, x2) - Math.min(newW, x1);
        long rightPaper = Math.min(f, x2) - Math.min(f, x1);
        long colorArea = (leftPaper+rightPaper)*(Math.abs(y1-y2))*(c+1);
        System.out.println(area - colorArea);
        scanner.close();
    }
}