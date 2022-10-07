package project;

import javax.swing.*;
import java.awt.*;

public final class CalculatorScreen {

    private final static JTextField textField;

    // Class does not need instances
    private CalculatorScreen() {}

    public static JTextField getTextField() {
        return textField;
    }

    static {
        textField = new JTextField();
        textField.setName("EquationTextField");
        textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
    }

    static void buttonPress(String buttonType) {
        if (buttonType.equals("=")) {
            performEqualsOperation();
        } else if (buttonType.matches("\\d") || previousCharIsNumericOrEmpty()) {
            textField.setText(textField.getText() + buttonType);
        }
    }

    private static boolean previousCharIsNumericOrEmpty() {
        String textFieldString = textField.getText();
        if (textFieldString.isBlank()) {
            return true;
        }

        String lastChar = textFieldString.substring(textFieldString.length() - 1);
        return lastChar.matches("\\d");
    }

    private static void performEqualsOperation() {
        String text = textField.getText();
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

        textField.setText(text);
    }

    private static void performAddition() {
        String[] numbers = textField.getText().split("\\+");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%d+%d = %d", firstNumber, secondNumber, firstNumber + secondNumber));
    }

    private static void performSubtraction() {
        String[] numbers = textField.getText().split("-");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%d-%d = %d", firstNumber, secondNumber, firstNumber - secondNumber));
    }

    private static void performMultiplication() {
        String[] numbers = textField.getText().split("x");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%dx%d = %d", firstNumber, secondNumber, firstNumber * secondNumber));
    }

    private static void performDivision() {
        String[] numbers = textField.getText().split("/");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        if (secondNumber == 0) {
            textField.setText("Error!");
        } else {
            textField.setText(String.format("%d/%d = %d", firstNumber, secondNumber, firstNumber / secondNumber));
        }
    }
}
