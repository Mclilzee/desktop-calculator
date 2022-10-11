package project;

import javax.swing.*;

public class LogicProcessor {

    private final JLabel resultScreen;
    private final JLabel equationScreen;
    private StringBuilder builder;

    public LogicProcessor(CalculatorScreen screen) {
        this.resultScreen = screen.getResultLabel();
        this.equationScreen = screen.getEquationLabel();
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
            default -> builder.append(type.getValue());
        }

        equationScreen.setText(builder.toString());
    }

    private void performEquation() {
        resultScreen.setText(builder.toString());
        equationScreen.setText(builder.toString());
    }
}
