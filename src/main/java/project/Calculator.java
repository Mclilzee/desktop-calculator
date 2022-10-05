package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator extends JFrame {

    private final JTextField textField;

    public Calculator(String title) {
        super(title);
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setMaximumSize(new Dimension(getWidth(), 50));

        textField = new JTextField();
        textField.setName("EquationTextField");
        textField.setAlignmentX(CENTER_ALIGNMENT);
        textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        panel.add(textField, BorderLayout.CENTER);

        add(panel);
        add(createButtonsPanel());

        setVisible(true);
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4, 4);
        layout.setHgap(20);
        layout.setVgap(20);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(0, 20, 20, 20));

        panel.add(createCalculatorButton("7", "Seven"));
        panel.add(createCalculatorButton("8", "Eight"));
        panel.add(createCalculatorButton("9", "Nine"));
        panel.add(createCalculatorButton("/", "Divide"));
        panel.add(createCalculatorButton("4", "Four"));
        panel.add(createCalculatorButton("5", "Five"));
        panel.add(createCalculatorButton("6", "Six"));
        panel.add(createCalculatorButton("x", "Multiply"));
        panel.add(createCalculatorButton("1", "One"));
        panel.add(createCalculatorButton("2", "Two"));
        panel.add(createCalculatorButton("3", "Three"));
        panel.add(createCalculatorButton("+", "Add"));
        panel.add(Box.createGlue());
        panel.add(createCalculatorButton("0", "Zero"));
        panel.add(createEqualButton());
        panel.add(createCalculatorButton("-", "Subtract"));
        return panel;
    }

    private JButton createCalculatorButton(String value, String name) {
        JButton button = new JButton(value);
        button.setName(name);
        attachActionListenerToButton(button);
        return button;
    }

    private void attachActionListenerToButton(JButton button) {
        button.addActionListener(e -> {
            String buttonType = button.getText();
            String text = textField.getText();
            if (!buttonType.matches("\\d") && text.contains(buttonType)) {
                return;
            }
            textField.setText(text + buttonType);
        });
    }

    private JButton createEqualButton() {
        JButton button = new JButton("=");
        button.setName("Equals");
        button.addActionListener(e -> equalOperation());

        return button;
    }

    private void equalOperation() {
        String text = textField.getText();
        if (text.contains("+")) {
            performAddition();
        }
//        } else if (text.contains("-")) {
//            performSubtraction();
//        } else if (text.contains("*")) {
//            performMultiplication();
//        } else {
//            performDivition();
//        }
    }

    private void performAddition() {
        String[] numbers = textField.getText().split("\\+");
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);

        textField.setText(String.format("%d+%d = %d", firstNumber, secondNumber, firstNumber + secondNumber));
    }
}
