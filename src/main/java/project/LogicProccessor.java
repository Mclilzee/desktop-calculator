package project;

import javax.swing.*;

public class LogicProccessor {

    private final JLabel resultScreen;
    private final JLabel equationScreen;
    private StringBuilder equationBuilder;

    public LogicProccessor(CalculatorScreen screen) {
        this.resultScreen = screen.getResultLabel();
        this.equationScreen = screen.getEquationLabel();
        this.equationBuilder = new StringBuilder();
    }

    public void buttonPress(CalculatorButtonsPanel.ButtonType type) {
        switch (type) {
            case CLEAR:
                equationScreen.setText("");
                resultScreen.setText("0");
                equationBuilder = new StringBuilder();
                break;
            default:
                equationBuilder.append(type.getValue());
                equationScreen.setText(equationBuilder.toString());
                break;
        }
    }
}
