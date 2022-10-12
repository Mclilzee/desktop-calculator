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
            case PARENTHESES -> insertParentheses();
            case POWER_TWO -> {
                insertPower();
                addNumber(ButtonType.TWO);
                insertParentheses();
            }
            case POWER_Y -> insertPower();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> addNumber(type);
        }

        if (type != ButtonType.EQUALS) {
            setEquationScreenFromStack();
        }
    }

    private void performEquation() {
        if (validInput()) {
            operationStack.add(getValidNumberBuilder());
            numberBuilder.setLength(0);
            CalculationHandler.displayResult(operationStack, resultScreen);
        } else {
            equationScreen.setForeground(Color.RED.darker());
        }
    }

    private boolean validInput() {
        if (operationStack.isEmpty() && !numberBuilder.isEmpty()) {
            return true;
        }

        return !endsWithOperator() && !hasZeroDivision();
    }

    private boolean endsWithOperator() {
        return !operationStack.isEmpty() && isOperator(operationStack.peekLast()) && numberBuilder.isEmpty();
    }

    private boolean hasZeroDivision() {
        String[] elements = operationStack.toArray(new String[0]);
        if (elements[elements.length - 1].equals(ButtonType.DIVIDE.VALUE) && numberBuilder.toString().equals("0")) {
            return true;
        }

        for (int i = 1; i < elements.length - 1; i++) {
            if (elements[i - 1].equals(ButtonType.DIVIDE.VALUE) && elements[i].equals("0")) {
                return true;
            }
        }

        return false;
    }

    private void insertPower() {
        addOperator(ButtonType.POWER);
        insertParentheses();
    }

    private void insertParentheses() {
        if (hasBalancedParentheses() || endsWithParentheses() || endsWithOperator()) {
            operationStack.add(ButtonType.OPEN_PARENTHESES.VALUE);
        } else {
            operationStack.add(numberBuilder.toString());
            operationStack.add(ButtonType.CLOSED_PARENTHESES.VALUE);
            numberBuilder.setLength(0);
        }
    }

    private boolean endsWithParentheses() {
        if (!numberBuilder.isEmpty() || operationStack.isEmpty()) {
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
        if (operationStack.isEmpty() && numberBuilder.isEmpty()) {
            return;
        }

        if (endsWithOperator()) {
            operationStack.pollLast();
        }

        insertOperator(type.VALUE);
    }

    private void insertOperator(String operator) {
        if (!numberBuilder.isEmpty()) {
            operationStack.add(getValidNumberBuilder());
            numberBuilder.setLength(0);
        }
        operationStack.add(operator);
    }

    private String getValidNumberBuilder() {
        if (numberBuilder.toString().startsWith(".")) {
            numberBuilder.replace(0, 1, "0.");
        }

        if (numberBuilder.toString().endsWith(".")) {
            numberBuilder.replace(numberBuilder.length() - 1, numberBuilder.length(), ".0");
        }

        return numberBuilder.toString();
    }

    private boolean isOperator(String text) {
        return text.length() == 1 && !text.matches("^(\\d|\\(|\\))");
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

    private void setEquationScreenFromStack() {
        StringBuilder equationBuilder = new StringBuilder();

        for (String element : operationStack) {
            equationBuilder.append(element);
        }

        equationBuilder.append(numberBuilder.toString());
        equationScreen.setText(equationBuilder.toString());
    }
}
