package project;

import javax.swing.*;
import java.awt.*;

import static java.awt.Component.CENTER_ALIGNMENT;

public abstract class CalculatorScreen {

    private static final JTextField textField;

    static {
        textField = new JTextField();
        textField.setName("EquationTextField");
        textField.setAlignmentX(CENTER_ALIGNMENT);
        textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
    }

    static JTextField getTextField() {
        return textField;
    }

    static void buttonPress(String buttonType) {
        String textFieldString = textField.getText();
        int length = textFieldString.length();

        if (length == 0) {
            return;
        }

        if (buttonType.matches("\\d") || previousCharIsNumeric()) {
            textField.setText(textFieldString + buttonType);
        }
    }

    private static boolean previousCharIsNumeric() {
        String textFieldString = textField.getText();
        String lastChar = textFieldString.substring(textFieldString.length() - 1);

        return !lastChar.matches("\\d");
    }

    static void equalOperation() {
        if (textField.getText().contains("+")) {
            performAddition();
        } else if (textField.getText().contains("-")) {
            performSubtraction();
        } else if (textField.getText().contains("x")) {
            performMultiplication();
        } else if (textField.getText().contains("/")) {
            performDivision();
        }
    }

    static void performAddition() {
        String[] numbers = textField.getText().split("\\+");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%d+%d = %d", firstNumber, secondNumber, firstNumber + secondNumber));
    }

    static void performSubtraction() {
        String[] numbers = textField.getText().split("-");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%d-%d = %d", firstNumber, secondNumber, firstNumber - secondNumber));
    }

    static void performMultiplication() {
        String[] numbers = textField.getText().split("x");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%dx%d = %d", firstNumber, secondNumber, firstNumber * secondNumber));
    }

    static void performDivision() {
        String[] numbers = textField.getText().split("/");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        if (secondNumber == 0) {
            textField.setText("Division by Zero!");
        } else {
            textField.setText(String.format("%d/%d = %d", firstNumber, secondNumber, firstNumber / secondNumber));
        }
    }
}
