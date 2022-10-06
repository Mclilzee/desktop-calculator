package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator extends JFrame {

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



        panel.add(CalculatorScreen.getTextField(), BorderLayout.CENTER);

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

        panel.add(new CalculatorButton("7", "Seven"));
        panel.add(new CalculatorButton("8", "Eight"));
        panel.add(new CalculatorButton("9", "Nine"));
        panel.add(new CalculatorButton("/", "Divide"));
        panel.add(new CalculatorButton("4", "Four"));
        panel.add(new CalculatorButton("5", "Five"));
        panel.add(new CalculatorButton("6", "Six"));
        panel.add(new CalculatorButton("x", "Multiply"));
        panel.add(new CalculatorButton("1", "One"));
        panel.add(new CalculatorButton("2", "Two"));
        panel.add(new CalculatorButton("3", "Three"));
        panel.add(new CalculatorButton("+", "Add"));
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
}
