package project;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class LogicProcessor {

    private final JLabel resultScreen;
    private final JLabel equationScreen;
    private final Deque<String> operationStack;
    private final Deque<String> postfixStack;
    private StringBuilder builder;

    public LogicProcessor(CalculatorScreen screen) {
        this.resultScreen = screen.getResultLabel();
        this.equationScreen = screen.getEquationLabel();
        this.operationStack = new ArrayDeque<>();
        this.postfixStack = new ArrayDeque<>();

        this.builder = new StringBuilder();
    }

    public void buttonPress(ButtonType type) {

        switch (type) {
            case CLEAR -> {
                equationScreen.setText("");
                resultScreen.setText("0");
                builder = new StringBuilder();
            }
            case DELETE -> builder.deleteCharAt(builder.length() - 1);
            case EQUALS -> performEquation();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> builder.append(type.VALUE);
        }
    }

    private void performEquation() {
        resultScreen.setText(builder.toString());
        equationScreen.setText(builder.toString());
    }

    private void addOperator(ButtonType type) {
        if (!operationStack.isEmpty() && isOperator(operationStack.peek())) {
            return;
        } else if (operationStack.isEmpty()) {
            operationStack.push("0");
        }

        operationStack.push(builder.toString());
        operationStack.push(type.VALUE);
        builder = new StringBuilder();
        appendToEquationScreen(type);
    }

    private boolean isOperator(String text) {
        return !text.matches("\\d+");
    }

    private void appendToEquationScreen(ButtonType type) {
        String text = equationScreen.getText();
        equationScreen.setText(text + type.VALUE);
    }
}
