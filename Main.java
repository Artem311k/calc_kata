import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(calc(scanner.nextLine()));
        }
    }

    public static String calc(String input) {
        Data.inputCheck(input);
        return Data.output(Calculator.calculate());
    }
}

class Data {
    static final List<String> arabicNumbers = new ArrayList<>(),
            romanNumbers = new ArrayList<>(),
            operators = new ArrayList<>();
    static String strX, strY, operator, numbersType;
    static {
        Stream.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10").forEach(arabicNumbers::add);
        Stream.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X").forEach(romanNumbers::add);
        Stream.of("*", "/", "+", "-").forEach(operators::add);
    }

    static void inputCheck(String userInput) {
        String[] userInputSplited = userInput.split(" ");
        if (userInput.length() >= 5 && userInput.length() <= 11 && userInputSplited.length == 3 && operators.contains(userInputSplited[1])) {
            operator = userInputSplited[1];
            if (arabicNumbers.contains(userInputSplited[0]) && arabicNumbers.contains(userInputSplited[2])) {
                strX = userInputSplited[0];
                strY = userInputSplited[2];
                operator = userInputSplited[1];
                numbersType = "Arabic";
            } else if (romanNumbers.contains(userInputSplited[0]) && romanNumbers.contains(userInputSplited[2])) {
                strX = arabicNumbers.get(romanNumbers.indexOf(userInputSplited[0]));
                strY = arabicNumbers.get(romanNumbers.indexOf(userInputSplited[2]));
                operator = userInputSplited[1];
                numbersType = "Roman";
            } else {
                throw new NumberFormatException("Wrong input");
            }
        } else {
            throw new NumberFormatException("Wrong input");
        }
    }

    static String output(int result) {
        if (numbersType.equals("Arabic")) {
            return String.valueOf(result);
        } else if (numbersType.equals("Roman") && result > 0) {
            return Converter.convert(result);
        } else {
            throw new NumberFormatException("Wrong result in roman numbers");
        }
    }
}

class Calculator {
    static final int X = Integer.parseInt(Data.strX), Y = Integer.parseInt(Data.strY);
    static int result;

    static int calculate() {
        switch (Data.operator) {
            case "*" -> result = X * Y;
            case "/" -> result = X / Y;
            case "+" -> result = X + Y;
            case "-" -> result = X - Y;
        }
        return result;
    }
}

class Converter {
    static final List<String> romanNumbers = new ArrayList<>();
    static {
        Stream.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
                "XCVII", "XCVIII", "XCIX", "C").forEach(romanNumbers::add);
    }

    static String convert(int arabicResult) {
        return romanNumbers.get(arabicResult - 1);
    }
}


