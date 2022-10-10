package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class CalculatorScreen {

    private final static JPanel panel;
    private final static JLabel resultLabel;
    private final static JLabel equationLabel;

    // Class does not need instances
    private CalculatorScreen() {}

    public static JPanel getPanel() {
        return panel;
    }

    static {
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
        equationLabel.setText("2+2");

        panel.add(resultLabel);
        panel.add(equationLabel);
    }

    static void buttonPress(String buttonType) {
        if (buttonType.equals("=")) {
            performEqualsOperation();
        } else if (buttonType.matches("\\d") || previousCharIsNumericOrEmpty()) {
            resultLabel.setText(resultLabel.getText() + buttonType);
        }
    }

    private static boolean previousCharIsNumericOrEmpty() {
        String textFieldString = resultLabel.getText();
        if (textFieldString.isBlank()) {
            return true;
        }

        String lastChar = textFieldString.substring(textFieldString.length() - 1);
        return lastChar.matches("\\d");
    }

    private static void performEqualsOperation() {
        String text = resultLabel.getText();
        if (!text.isEmpty()) {
            addMissingValues(text);
        }

        if (text.contains("+")) {
            performAddition();
        } else if (text.contains("-")) {
            performSubtraction();
        } else if (text.contains("x")) {
            performMultiplication();
        } else if (text.contains("/")) {
            performDivision();
        }
    }

    private static void addMissingValues(String text) {
        if (!String.valueOf(text.charAt(0)).matches("\\d")) {
            text = "0" + text;
        }

        if (!String.valueOf(text.charAt(text.length() - 1)).matches("\\d")) {
            text += "0";
        }

        resultLabel.setText(text);
    }

    private static void performAddition() {
        String[] numbers = resultLabel.getText().split("\\+");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        resultLabel.setText(String.format("%d+%d = %d", firstNumber, secondNumber, firstNumber + secondNumber));
    }

    private static void performSubtraction() {
        String[] numbers = resultLabel.getText().split("-");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        resultLabel.setText(String.format("%d-%d = %d", firstNumber, secondNumber, firstNumber - secondNumber));
    }

    private static void performMultiplication() {
        String[] numbers = resultLabel.getText().split("x");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        resultLabel.setText(String.format("%dx%d = %d", firstNumber, secondNumber, firstNumber * secondNumber));
    }

    private static void performDivision() {
        String[] numbers = resultLabel.getText().split("/");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        if (secondNumber == 0) {
            resultLabel.setText("Error!");
        } else {
            resultLabel.setText(String.format("%d/%d = %d", firstNumber, secondNumber, firstNumber / secondNumber));
        }
    }
}
