import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str="";
        int sum = 0;
        while (!str.equals("=")){
            str = br.readLine();
            if(Character.isDigit(str.charAt(0))){
                sum = Integer.parseInt(str);
            }
            else{
                int operand;
                switch (str){
                    case "+":
                        operand = Integer.parseInt(br.readLine());
                        sum += operand;
                        break;
                    case "-":
                        operand = Integer.parseInt(br.readLine());
                        sum -= operand;
                        break;
                    case "*":
                        operand = Integer.parseInt(br.readLine());
                        sum *= operand;
                        break;
                    case "/":
                        operand = Integer.parseInt(br.readLine());
                        sum /= operand;
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(sum);
    }

}
