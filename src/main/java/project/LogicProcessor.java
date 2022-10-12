package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class LogicProcessor {

    private final JLabel resultScreen;
    private final JLabel equationScreen;
    private final Deque<String> operationStack;
    private final StringBuilder numberBuilder;

    public LogicProcessor(CalculatorScreen screen) {
        this.resultScreen = screen.getResultLabel();
        this.equationScreen = screen.getEquationLabel();
        this.operationStack = new ArrayDeque<>();

        this.numberBuilder = new StringBuilder();
    }

    public void buttonPress(ButtonType type) {
        if (equationScreen.getForeground().equals(Color.RED.darker())) {
            equationScreen.setForeground(Color.decode("#00b135"));
        }

        switch (type) {
            case CLEAR -> {
                resultScreen.setText("0");
                numberBuilder.setLength(0);
                operationStack.clear();
            }
            case CLEAR_ENTRY -> numberBuilder.setLength(0);
            case DELETE -> deleteLastCharacter();
            case EQUALS -> performEquation();
            case PARENTHESES -> addParentheses();
            case SQUARE_ROOT -> addSquareRoot();
            case POWER_TWO -> {
                addPower();
                addNumber(ButtonType.TWO);
                addParentheses();
            }
            case POWER_Y -> addPower();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> addNumber(type);
        }

        if (type != ButtonType.EQUALS) {
            setEquationScreenFromStack();
        }
    }

    private void performEquation() {
        addValidNumberBuilder();
        if (validInput()) {
            CalculationHandler.displayResult(operationStack, resultScreen);
        } else {
            equationScreen.setForeground(Color.RED.darker());
        }
    }

    private boolean validInput() {
        return !operationStack.isEmpty() && !endsWithOperator() && !hasZeroDivision();
    }

    private boolean endsWithOperator() {
        return !operationStack.isEmpty() && isOperator(operationStack.peekLast());
    }

    private boolean hasZeroDivision() {
        String[] elements = operationStack.toArray(new String[0]);
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i].equals(ButtonType.DIVIDE.VALUE) && elements[i + 1].equals("0")) {
                return true;
            }
        }

        return false;
    }

    private void deleteLastCharacter() {
        if (!numberBuilder.isEmpty()) {
            numberBuilder.deleteCharAt(numberBuilder.length() - 1);
            return;
        }

        String lastElement = operationStack.isEmpty() ? "" : operationStack.pollLast();
        if (!lastElement.isEmpty() && !isOperator(lastElement)) {
            numberBuilder.append(lastElement, 0, lastElement.length() - 1);
        }
    }

    private void addSquareRoot() {
        addOperator(ButtonType.SQUARE_ROOT);
        addParentheses();
    }

    private void addPower() {
        addOperator(ButtonType.POWER);
        addParentheses();
    }

    private void addParentheses() {
        addValidNumberBuilder();
        if (hasBalancedParentheses() || endsWithParentheses() || endsWithOperator()) {
            operationStack.add(ButtonType.OPEN_PARENTHESES.VALUE);
        } else {
            operationStack.add(ButtonType.CLOSED_PARENTHESES.VALUE);
        }
    }

    private boolean endsWithParentheses() {
        if (operationStack.isEmpty()) {
            return false;
        }
        return operationStack.peekLast().equals(ButtonType.OPEN_PARENTHESES.VALUE);
    }

    private boolean hasBalancedParentheses() {
        int count = 0;
        for (String each : operationStack) {
            if (each.equals(ButtonType.OPEN_PARENTHESES.VALUE)) {
                count++;
            } else if (each.equals(ButtonType.CLOSED_PARENTHESES.VALUE)) {
                count--;
            }
        }

        return count == 0;
    }

    private void addNumber(ButtonType type) {
        if (type == ButtonType.ZERO) {
            addZero();
        } else if (type == ButtonType.DOT) {
            addDot();
        } else {
            insertNumber(type.VALUE);
        }
    }

    private void insertNumber(String value) {
        if (numberBuilder.length() == 1 && numberBuilder.charAt(0) == '0') {
            numberBuilder.deleteCharAt(0);
        }

        numberBuilder.append(value);
    }

    private void addZero() {
        if (numberBuilder.length() != 1 || numberBuilder.charAt(0) != '0') {
            numberBuilder.append("0");
        }
    }

    private void addDot() {
        if (numberBuilder.isEmpty() || !numberBuilder.toString().contains(".")) {
            numberBuilder.append(".");
        }
    }

    private void addOperator(ButtonType type) {
        addValidNumberBuilder();
        if (operationStack.isEmpty() && type != ButtonType.SQUARE_ROOT) {
            return;
        }
        if (endsWithOperator()) {
            operationStack.pollLast();
        }

        operationStack.add(type.VALUE);
    }

    private void addValidNumberBuilder() {
        if (numberBuilder.isEmpty()) {
            return;
        }

        if (numberBuilder.toString().startsWith(".")) {
            numberBuilder.replace(0, 1, "0.");
        }

        if (numberBuilder.toString().endsWith(".")) {
            numberBuilder.replace(numberBuilder.length() - 1, numberBuilder.length(), ".0");
        }

        operationStack.add(numberBuilder.toString());
        numberBuilder.setLength(0);
    }

    private boolean isOperator(String text) {
        return text.length() == 1 && !text.equals(ButtonType.SQUARE_ROOT.VALUE) &&
                !text.matches("^(\\d|\\(|\\))");
    }

    private void setEquationScreenFromStack() {
        StringBuilder equationBuilder = new StringBuilder();

        for (String element : operationStack) {
            equationBuilder.append(element);
        }

        equationBuilder.append(numberBuilder.toString());
        equationScreen.setText(equationBuilder.toString());
    }
}
