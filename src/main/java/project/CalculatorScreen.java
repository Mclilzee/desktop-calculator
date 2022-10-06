package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorScreen extends JPanel {

    private final static JTextField textField;

    public CalculatorScreen(Dimension dimension) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setMaximumSize(dimension);

        add(textField, BorderLayout.CENTER);
    }

    public CalculatorScreen(int width, int height) {
        this(new Dimension(width, height));
    }

    static {
        textField = new JTextField();
        textField.setName("EquationTextField");
        textField.setAlignmentX(CENTER_ALIGNMENT);
        textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
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
            textField.setText("Division by Zero!");
        } else {
            textField.setText(String.format("%d/%d = %d", firstNumber, secondNumber, firstNumber / secondNumber));
        }
    }
}
