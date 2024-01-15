import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String romanExpressionOne = sc.nextLine();
        String romanExpressionTwo = sc.nextLine();

        RomanExpressionParser romanExpressionParser = new RomanExpressionParser();
        romanExpressionParser.initializeDictionary();
        romanExpressionParser.parse(romanExpressionOne);
        int sum = romanExpressionParser.getArabianInteger();
        romanExpressionParser.clearResult();
        romanExpressionParser.parse(romanExpressionTwo);
        sum += romanExpressionParser.getArabianInteger();
        romanExpressionParser.clearResult();

        ArabianExpressionConverter arabianExpressionConverter = new ArabianExpressionConverter();
        arabianExpressionConverter.initialize();
        arabianExpressionConverter.convert(sum);
        System.out.println(sum);
        System.out.println(arabianExpressionConverter.getRomanExpression());

    }
}

class RomanExpressionParser {
    private final HashMap<String, Integer> translationDictionary;

    private int arabianInteger;

    public RomanExpressionParser() {
        this.arabianInteger = 0;
        this.translationDictionary = new HashMap<>();
    }

    public void initializeDictionary() {
        translationDictionary.put("I", 1);
        translationDictionary.put("IV", 4);

        translationDictionary.put("V", 5);
        translationDictionary.put("VI", 6);

        translationDictionary.put("IX", 9);
        translationDictionary.put("X", 10);

        translationDictionary.put("L", 50);
        translationDictionary.put("XL", 40);

        translationDictionary.put("C", 100);
        translationDictionary.put("XC", 90);

        translationDictionary.put("D", 500);
        translationDictionary.put("CD", 400);

        translationDictionary.put("M", 1000);
        translationDictionary.put("CM", 900);
    }

    public void parse(String expression) {
        int index = 0;
        while (index < expression.length()) {
            if (index + 2 <= expression.length() && translationDictionary.containsKey(expression.substring(index, index + 2))) {
                this.arabianInteger += translationDictionary.get(expression.substring(index, index + 2));
                index++;
            } else if (translationDictionary.containsKey(expression.substring(index, index + 1))) {
                this.arabianInteger += translationDictionary.get(expression.substring(index, index + 1));
            }

            index++;
        }
    }

    public int getArabianInteger() {
        return this.arabianInteger;
    }

    public void clearResult() {
        this.arabianInteger = 0;
    }
}

class ArabianExpressionConverter {
    private static final List<Integer> NUMBERS = List.of(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
    private String romanExpression;
    private final HashMap<Integer, String> translationDictionary;

    public ArabianExpressionConverter() {
        this.translationDictionary = new HashMap<>();
    }

    public void initialize(){
        translationDictionary.put(1, "I" );
        translationDictionary.put(4, "IV");

        translationDictionary.put(5, "V");
        translationDictionary.put(6, "VI");

        translationDictionary.put(9, "IX");
        translationDictionary.put(10, "X");

        translationDictionary.put(50, "L");
        translationDictionary.put(40, "XL");

        translationDictionary.put(100, "C");
        translationDictionary.put(90, "XC");
        translationDictionary.put(500, "D");
        translationDictionary.put(400, "CD");

        translationDictionary.put(1000, "M");
        translationDictionary.put(900, "CM");
    }

    public void convert(int arabianExpression) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (arabianExpression > 0){

            if(arabianExpression >= NUMBERS.get(index)){
                sb.append(translationDictionary.get(NUMBERS.get(index)));
                arabianExpression -= NUMBERS.get(index);
            }
            else {
                index++;
            }
        }
        this.romanExpression = sb.toString();
    }
    public String getRomanExpression(){
        return this.romanExpression;
    }
}