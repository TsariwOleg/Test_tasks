import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int number = main.inputNumber();
        System.out.println(main.increaseEveryDigits(number));
    }


    public  int inputNumber() {
        int number = 0;
        boolean trigger = true;
        while (trigger) {
            Scanner scanner = new Scanner(System.in);
            try {
                number = scanner.nextInt();
                trigger = false;
            } catch (InputMismatchException e) {
                System.out.println("Input correct number");
            }
        }
        return number;
    }

    public  int increaseEveryDigits(int number) {
        String stringNumber = number + "";
        StringBuilder result = new StringBuilder();
        if (stringNumber.charAt(0) == '-') {
            result.append("-");
        }
        stringNumber.chars().mapToObj(x->(char)x).
                map(Character::getNumericValue).
                forEach(x-> result.append(++x));

        return Integer.parseInt(result.toString());
    }

}
