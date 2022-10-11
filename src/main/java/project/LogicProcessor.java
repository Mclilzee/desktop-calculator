package project;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class LogicProcessor {

    private final JLabel resultScreen;
    private final JLabel equationScreen;
    private final Deque<String> operationStack;
    private final Deque<String> postfixStack;
    private final StringBuilder numberBuilder;

    public LogicProcessor(CalculatorScreen screen) {
        this.resultScreen = screen.getResultLabel();
        this.equationScreen = screen.getEquationLabel();
        this.operationStack = new ArrayDeque<>();
        this.postfixStack = new ArrayDeque<>();

        this.numberBuilder = new StringBuilder();
    }

    public void buttonPress(ButtonType type) {

        switch (type) {
            case CLEAR -> {
                equationScreen.setText("");
                resultScreen.setText("0");
                numberBuilder.setLength(0);
            }
            case DELETE -> numberBuilder.deleteCharAt(numberBuilder.length() - 1);
            case EQUALS -> performEquation();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> numberBuilder.append(type.VALUE);
        }


        setEquationScreenFromStack();
    }

    private void performEquation() {
        resultScreen.setText(numberBuilder.toString());
        equationScreen.setText(numberBuilder.toString());
    }

    private void addOperator(ButtonType type) {
        if (!operationStack.isEmpty() && isOperator(operationStack.peek())) {
            return;
        }

        if (numberBuilder.isEmpty()) {
            numberBuilder.append("0");
        }

        operationStack.push(numberBuilder.toString());
        operationStack.push(type.VALUE);
        numberBuilder.setLength(0);
    }

    private boolean isOperator(String text) {
        return text.length() == 1 && !text.matches("^\\d");
    }

    private void setEquationScreenFromStack() {
        StringBuilder equationBuilder = new StringBuilder();
        for (String text : operationStack) {
            equationBuilder.append(text);
        }

        equationBuilder.append(numberBuilder.toString());
        equationScreen.setText(equationBuilder.toString());
    }
}
