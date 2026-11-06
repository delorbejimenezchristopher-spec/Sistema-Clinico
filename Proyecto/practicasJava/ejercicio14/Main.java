public class Main {

    public static void main(String[] args) {

        CalculatorConundrum calculator = new CalculatorConundrum();

        System.out.println(calculator.calculate(16, 51, "+"));
        System.out.println(calculator.calculate(32, 6, "*"));
        System.out.println(calculator.calculate(512, 4, "/"));

        try {
            calculator.calculate(10, 1, "-");
        } catch (IllegalOperationException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            calculator.calculate(512, 0, "/");
        } catch (IllegalOperationException exception) {
            System.out.println(exception.getMessage());
        }
    }
}