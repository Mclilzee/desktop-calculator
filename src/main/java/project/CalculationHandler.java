package project;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Deque;

public final class CalculationHandler {

    private CalculationHandler() {
    }

    private static final Deque<String> operators = new ArrayDeque<>();
    private static final Deque<BigDecimal> postfixStack = new ArrayDeque<>();

    public static void displayResult(Deque<String> operationStack, JLabel resultLabel) {

        while (!operationStack.isEmpty()) {
            String element = operationStack.pop();
            if (isNumber(element)) {
                postfixStack.push(new BigDecimal(element));
            } else if (element.equals(ButtonType.CLOSED_PARENTHESES.VALUE)) {
                emptyStack();
            } else {
                performOperatorInsertion(element);
            }
        }

        emptyStack();

        operationStack.add(postfixStack.pop().toPlainString());
        resultLabel.setText(operationStack.peek());
    }

    private static void emptyStack() {
        while (!operators.isEmpty()) {
            if (operators.peek().equals(ButtonType.OPEN_PARENTHESES.VALUE)) {
                operators.pop();
                break;
            }

            performCalculation();
        }
    }

    private static void performOperatorInsertion(String element) {
        if (postfixStack.size() < 2) {
            operators.push(element);
        } else {
            performPrecedenceInsertion(element);
        }
    }

    private static void performPrecedenceInsertion(String element) {
        ButtonType type = getButtonType(element);

        while (!operators.isEmpty()) {
            ButtonType previousType = getButtonType(operators.peek());
            if (type.PRECEDENCE <= previousType.PRECEDENCE && previousType != ButtonType.OPEN_PARENTHESES) {
                performCalculation();
            } else {
                break;
            }
        }

        operators.push(type.VALUE);
    }

    private static void performCalculation() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        ButtonType operatorType = getButtonType(operators.pop());

        switch (operatorType) {
            case ADD -> postfixStack.push(firstNumber.add(secondNumber).stripTrailingZeros());
            case SUBTRACT -> postfixStack.push(firstNumber.subtract(secondNumber).stripTrailingZeros());
            case MULTIPLY -> postfixStack.push(firstNumber.multiply(secondNumber).stripTrailingZeros());
            case DIVIDE ->
                    postfixStack.push(firstNumber.divide(secondNumber, 10, RoundingMode.CEILING).stripTrailingZeros());
        }
    }

    private static ButtonType getButtonType(String element) {
        for (ButtonType type : ButtonType.values()) {
            if (type.VALUE.equals(element)) {
                return type;
            }
        }

        throw new InvalidParameterException();
    }

    private static boolean isNumber(String text) {
        return text.length() != 1 || text.matches("^\\d");
    }
}

