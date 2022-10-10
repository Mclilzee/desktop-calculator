package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigInteger;

public final class CalculatorScreen {

    private final static JPanel panel;
    private final static JLabel resultLabel;
    private final static JLabel equationLabel;
    private final static StringBuilder numberString;

    // Class does not need instances
    private CalculatorScreen() {}

    public static JPanel getPanel() {
        return panel;
    }

    static {
        numberString = new StringBuilder();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        resultLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        resultLabel.setText("0");

        equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        equationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        panel.add(resultLabel);
        panel.add(equationLabel);
    }

    static void buttonPress(ButtonType type, String value) {
        if (type == ButtonType.Number) {
            addNumber(value);
        }
    }

    private static void addNumber(String number) {
        equationLabel.setText(equationLabel.getText() + number);

        numberString.append(number);
    }

    private static void performEqualsOperation() {

    }
}
