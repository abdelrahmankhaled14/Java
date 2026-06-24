import java.util.Scanner;



interface Operation {
    double apply(double a, double b);
}

class Addition implements Operation {
    @Override
    public double apply(double a, double b) {
        return a + b;
    }
}
class Subtract implements Operation {
    @Override
    public double apply(double a, double b) {
        return a - b;
    }
}
class Multiply implements Operation {
    @Override
    public double apply(double a, double b) {
        return a * b;
    }
}
class Divide implements Operation {
    @Override
    public double apply(double a, double b) {
        return a / b;
    }
}

class OperationFactory {
    public Operation create(char operator) {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Subtract();
            case '*':
                return new Multiply();
            case '/':
                return new Divide();
            default:
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your number 1: ");
        double num1 = scanner.nextDouble(); 

        System.out.print("Enter operator: ");
        char operator = scanner.next().charAt(0); 

        System.out.print("Enter your number 2: ");
        double num2 = scanner.nextDouble();


        OperationFactory factory = new OperationFactory();
        Operation op = factory.create(operator);
        double result = op.apply(num1, num2);

        System.out.println("Result: " + result);


    }
}