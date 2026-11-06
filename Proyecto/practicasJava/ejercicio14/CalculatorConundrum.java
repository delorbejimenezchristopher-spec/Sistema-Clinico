public class CalculatorConundrum {

    public String calculate(int operand1, int operand2, String operation) {

        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        try {

            int result;

            switch (operation) {
                case "+":
                    result = operand1 + operand2;
                    break;

                case "*":
                    result = operand1 * operand2;
                    break;

                case "/":
                    result = operand1 / operand2;
                    break;

                default:
                    throw new IllegalOperationException(
                        "Operation '" + operation + "' does not exist"
                    );
            }

            return operand1 + " " + operation + " " + operand2 + " = " + result;

        } catch (ArithmeticException exception) {

            throw new IllegalOperationException(
                "Division by zero is not allowed",
                exception
            );
        }
    }
}