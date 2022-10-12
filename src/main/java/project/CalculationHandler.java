package project;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.*;

public final class CalculationHandler {

    private CalculationHandler() {
    }

    private static final Deque<String> operators = new ArrayDeque<>();
    private static final Deque<BigDecimal> postfixStack = new ArrayDeque<>();

    public static void displayResult(Deque<String> operationStack, JLabel resultLabel) {
        Deque<String> copyOperationStack = new ArrayDeque<>(operationStack);
        calculateStacks(copyOperationStack);

        if (!operators.isEmpty()) {
            emptyStack();
        }

        String result = postfixStack.pop().toPlainString();
        operationStack.clear();
        operationStack.add(result);
        resultLabel.setText(operationStack.peek());
    }

    private static void calculateStacks(Deque<String> copyOperationStack) {
        while (!copyOperationStack.isEmpty()) {
            String element = copyOperationStack.pop();
            if (isNumber(element)) {
                postfixStack.push(new BigDecimal(element));
            } else if (element.equals(ButtonType.CLOSED_PARENTHESES.VALUE)) {
                emptyStack();
            } else {
                performOperatorInsertion(element);
            }
        }
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
        ButtonType operatorType = getButtonType(operators.pop());

        switch (operatorType) {
            case ADD -> performAddition();
            case SUBTRACT -> performSubtraction();
            case MULTIPLY -> performMultiplication();
            case DIVIDE -> performDivision();
            case POWER -> performExponentiation();
            case SQUARE_ROOT -> performSquareRootOperation();
        }
    }

    private static void performAddition() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        BigDecimal result = secondNumber.add(firstNumber);
        postfixStack.push(result.stripTrailingZeros());
    }

    private static void performSubtraction() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        BigDecimal result = secondNumber.subtract(firstNumber);
        postfixStack.push(result.stripTrailingZeros());
    }

    private static void performMultiplication() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        BigDecimal result = firstNumber.multiply(secondNumber);
        postfixStack.push(result.stripTrailingZeros());
    }

    private static void performDivision() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        BigDecimal result = firstNumber.divide(secondNumber, 10, RoundingMode.CEILING);
        postfixStack.push(result.stripTrailingZeros());
    }

    private static void performExponentiation() {
        BigDecimal secondNumber = postfixStack.pop();
        BigDecimal firstNumber = postfixStack.pop();
        BigDecimal result = firstNumber.pow(secondNumber.intValue());
        postfixStack.push(result.stripTrailingZeros());
    }

    private static void performSquareRootOperation() {
        BigDecimal number = postfixStack.pop();
        postfixStack.push(number.sqrt(new MathContext(20)));
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

