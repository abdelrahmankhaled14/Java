import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your number 1: ");
        double num1 = scanner.nextDouble(); 

        System.out.print("Enter operator: ");
        char operator = scanner.next().charAt(0); 

        System.out.print("Enter your number 2: ");
        double num2 = scanner.nextDouble();

        double result = 0;

        if (operator == '/') {

            if (num2 == 0) {
                System.out.println("Error: cannot divide by zero");
                return;  
            }
            result = num1 / num2;
        } else if (operator == '*') {
            result = num1 * num2;
        } else if (operator == '-') {
            result = num1 - num2;
        } else if (operator == '+') {
            result = num1 + num2;
        } else {
            System.out.println("invalid operator");
            return; 
        }

        System.out.println("Result: " + result);
    }
}