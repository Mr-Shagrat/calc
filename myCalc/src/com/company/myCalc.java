package com.company;
import java.util.*;

public class myCalc {

    public static void main(String[] args) {

        try{

            Scanner in = new Scanner(System.in);
            System.out.println("Input:");
            String input = in.nextLine().replace(" ","");

            if (input.length() < 3){
                throw new Exception();
            }
            if(input.contains(",") || input.contains(".")){
                throw new Exception();
            }
            if (input.substring(input.length()-1).matches("[+-/*]") ){
                throw new Exception();
            }

            Main expression = new Main();
            System.out.println("Output:\n" + expression.calc(input));
        }catch (Exception ex){
            System.out.println("Output:\nИсключение");
        }
    }

}

class Main {

    String[] num = null;
    char operation;
    int firstNumber;
    int secondNumber;
    int result;
    boolean isRoman = false;

    public String calc(String inputString) throws Exception {

        this.num = inputString.toUpperCase(Locale.ROOT).split("[+-/*]");

        if (num.length > 2) throw new Exception();

        this.operation = inputString.charAt(num[0].length());
        this.isRoman = isRomanNumber(num);

        if (isRoman){
            this.firstNumber = romanToArabic(num[0].toUpperCase(Locale.ROOT));
            this.secondNumber = romanToArabic(num[1].toUpperCase(Locale.ROOT));
        } else {
            this.firstNumber = Integer.parseInt(num[0]);
            this.secondNumber = Integer.parseInt(num[1]);
        }

        this.result = calculate(firstNumber, secondNumber, operation);

        if(isRoman) return arabicToRoman(result);

        return String.valueOf(result);
    }

    int romanToArabic (String number) {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX","X"};
        int i = 0;

        do {
            if (roman[i].equals(number)) {
                return i + 1;
            }
            i++;
        } while (i < roman.length);

        return 0;
    }

    String arabicToRoman(int inputValue) throws Exception {
        String[] romanNumber = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        if (inputValue < 1) throw new Exception();

        return romanNumber[inputValue-1];
    }

    int calculate(int a, int b, char operation) throws Exception {
        return result = switch (operation){
            case'+' -> a + b;
            case'-' -> a - b;
            case'*' -> a * b;
            case'/' -> Math.round(a/b);
            default -> throw new Exception();
        };
    }

    boolean isRomanNumber (String[] num) throws Exception {

        if(num[0].matches("^([1-9]|10)$") && num[1].matches("^([1-9]|10)$")){
            return false;
        } else if(num[0].matches("X?(IX|IV|V?I{0,3})$") && num[1].matches("X?(IX|IV|V?I{0,3})$")){
            return true;
        }

        throw new Exception();
    }

}