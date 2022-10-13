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

        performAction(type);

        if (type != ButtonType.EQUALS) {
            setEquationScreenFromStack();
        }
    }

    private void performAction(ButtonType type) {
        switch (type) {
            case CLEAR -> clearScreen();
            case CLEAR_ENTRY -> numberBuilder.setLength(0);
            case DELETE -> deleteLastCharacter();
            case EQUALS -> handleEquation();
            case PLUS_MINUS -> negateNumber();
            case PARENTHESES -> addParentheses();
            case SQUARE_ROOT -> addSquareRoot();
            case POWER_TWO -> addPowerOfTwo();
            case POWER_Y -> addPower();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> addNumber(type);
        }
    }

    private void clearScreen() {
        resultScreen.setText("0");
        numberBuilder.setLength(0);
        operationStack.clear();
    }

    private void handleEquation() {
        addValidNumberBuilder();
        if (isNegated()) {
            formatNegation();
        }
        if (validInput()) {
            performEquation();
        } else {
            equationScreen.setForeground(Color.RED.darker());
        }
    }

    private void formatNegation() {
        operationStack.pop();
        operationStack.pop();
        operationStack.push(ButtonType.OPEN_PARENTHESES.VALUE);
        operationStack.push(ButtonType.MULTIPLY.VALUE);
        operationStack.push("-1");
    }

    private void performEquation() {
        try {
            balanceParentheses();
            CalculationHandler.displayResult(operationStack, resultScreen);
        } catch (Exception e) {
            equationScreen.setForeground(Color.RED.darker());
        }
    }

    private boolean validInput() {
        return !operationStack.isEmpty() && !endsWithOperator() && !endsWithParentheses() && !hasZeroDivision();
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

    private void negateNumber() {
        addValidNumberBuilder();
        if (operationStack.size() < 2) {
            operationStack.push(ButtonType.SUBTRACT.VALUE);
            operationStack.push(ButtonType.OPEN_PARENTHESES.VALUE);
        } else {
            switchNegation();
        }
    }

    private void switchNegation() {
        if (isNegated()) {
            operationStack.pop();
            operationStack.pop();
            removeUnnecessaryParentheses();
        } else {
            performNegation();
        }

        if (noneClosedParentheses() < 0) {
            operationStack.pollLast();
        }
    }

    private boolean isNegated() {
        if (operationStack.size() < 2) {
            return false;
        }
        String firstElement = operationStack.pop();
        String secondElement = operationStack.peek();
        operationStack.push(firstElement);

        return firstElement.equals(ButtonType.OPEN_PARENTHESES.VALUE) && secondElement.equals(ButtonType.SUBTRACT.VALUE);
    }

    private void performNegation() {
        if (operationStack.size() > 2) {
            operationStack.push(ButtonType.OPEN_PARENTHESES.VALUE);
            operationStack.push(ButtonType.SUBTRACT.VALUE);
            operationStack.push(ButtonType.OPEN_PARENTHESES.VALUE);
            operationStack.add(ButtonType.CLOSED_PARENTHESES.VALUE);
            operationStack.add(ButtonType.CLOSED_PARENTHESES.VALUE);
        }
    }

    private void removeUnnecessaryParentheses() {
        if (operationStack.size() < 2) {
            return;
        }

        if (operationStack.peek().equals(ButtonType.OPEN_PARENTHESES.VALUE) && operationStack.peekLast().equals(ButtonType.CLOSED_PARENTHESES.VALUE)) {
            operationStack.pop();
            operationStack.pollLast();
        }
    }

    private void balanceParentheses() {
        int count = noneClosedParentheses();
        for (int i = 0; i < count; i++) {
            operationStack.add(ButtonType.CLOSED_PARENTHESES.VALUE);
        }
    }

    private void addPowerOfTwo() {
        addPower();
        addNumber(ButtonType.TWO);
        addParentheses();
    }

    private void addPower() {
        addOperator(ButtonType.POWER);
        addParentheses();
    }

    private void addSquareRoot() {
        addOperator(ButtonType.SQUARE_ROOT);
        addParentheses();
    }

    private void addParentheses() {
        addValidNumberBuilder();
        if (noneClosedParentheses() == 0 || endsWithParentheses() || endsWithOperator()) {
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

    private int noneClosedParentheses() {
        int count = 0;
        for (String each : operationStack) {
            if (each.equals(ButtonType.OPEN_PARENTHESES.VALUE)) {
                count++;
            } else if (each.equals(ButtonType.CLOSED_PARENTHESES.VALUE)) {
                count--;
            }
        }

        return count;
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
        } else if (operationStack.size() == 1 && operationStack.peek().matches("^\\d.*")) {
            return;
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
        if (type != ButtonType.SQUARE_ROOT && endsWithOperator()) {
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
