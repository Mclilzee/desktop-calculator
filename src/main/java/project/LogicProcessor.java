package project;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutionException;

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
        switch (type) {
            case CLEAR -> {
                resultScreen.setText("0");
                numberBuilder.setLength(0);
                operationStack.clear();
            }
            case DELETE -> deleteLastCharacter();
            case EQUALS -> performEquation();
            case ADD, MULTIPLY, DIVIDE, SUBTRACT -> addOperator(type);
            default -> addNumber(type);
        }

        setEquationScreenFromStack();
    }

    private void performEquation() {
        if (numberBuilder.isEmpty() && operationStack.isEmpty()) {
            return;
        }

        operationStack.push(numberBuilder.toString());
        numberBuilder.setLength(0);

        new ProcessorWorker().execute();
    }

    private int getParenthesesDifference() {
        int openParentheses = 0;
        int closedParentheses = 0;

        for (String element : operationStack) {
            if (element.equals("(")) {
                openParentheses++;
            } else if (element.equals(")")) {
                closedParentheses++;
            }
        }

        return openParentheses - closedParentheses;
    }

    private void addNumber(ButtonType type) {
        if (type == ButtonType.ZERO) {
            addZero();
        } else if (type == ButtonType.DOT) {
            addDot();
        } else {
            if (numberBuilder.length() == 1 && numberBuilder.charAt(0) == '0') {
                numberBuilder.deleteCharAt(0);
            }

            numberBuilder.append(type.VALUE);
        }
    }

    private void addZero() {
        if (numberBuilder.length() != 1 || numberBuilder.charAt(0) != '0') {
            numberBuilder.append("0");
        }
    }

    private void addDot() {
        if (numberBuilder.isEmpty()) {
            numberBuilder.append("0").append(".");
        } else if (!numberBuilder.toString().contains(".")) {
            numberBuilder.append(".");
        }
    }

    private void addOperator(ButtonType type) {
        if (!operationStack.isEmpty() && numberBuilder.isEmpty()) {
            return;
        } else if (numberBuilder.isEmpty()) {
            numberBuilder.append("0");
        }

        insertOperator(type.VALUE);
    }

    private void insertOperator(String operator) {
        trimNumberBuilder();
        operationStack.push(numberBuilder.toString());
        operationStack.push(operator);
        numberBuilder.setLength(0);
    }

    private void trimNumberBuilder() {
        while (numberBuilder.toString().contains(".")) {
            if (numberBuilder.toString().endsWith("0") || numberBuilder.toString().endsWith(".")) {
                numberBuilder.deleteCharAt(numberBuilder.length() - 1);
            } else {
                break;
            }
        }
    }

    private boolean isOperator(String text) {
        return text.length() == 1 && !text.matches("^\\d");
    }

    private void deleteLastCharacter() {
        // if numberBuilder is empty then last operation is an operator, remove from stack
        if (numberBuilder.isEmpty() && !operationStack.isEmpty()) {
            String lastElement = operationStack.pop();

            if (!isOperator(lastElement)) {
                numberBuilder.append(lastElement);
            }
        }

        if (!numberBuilder.isEmpty()) {
            numberBuilder.deleteCharAt(numberBuilder.length() - 1);
        }
    }

    private void setEquationScreenFromStack() {
        StringBuilder equationBuilder = new StringBuilder();
        String[] stackArray = operationStack.toArray(new String[0]);

        for (int i = stackArray.length - 1; i >= 0; i--) {
            equationBuilder.append(stackArray[i]);
        }

        equationBuilder.append(numberBuilder.toString());
        equationScreen.setText(equationBuilder.toString());
    }

    private class ProcessorWorker extends SwingWorker<String, Object> {

        @Override
        protected String doInBackground() throws Exception {
            Deque<String> operators = new ArrayDeque<>();

            while (!operationStack.isEmpty()) {
                String item = operationStack.pop();
                if (isOperator(item)) {
                }
            }

            return "Text Run";
        }

        @Override
        protected void done() {
            try {
                resultScreen.setText(get());
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }
    }
}
