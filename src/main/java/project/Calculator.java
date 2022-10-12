package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculator extends JFrame {

    public Calculator(String title) {
        super(title);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        CalculatorScreen screen = new CalculatorScreen();
        panel.add(screen);
        panel.add(new CalculatorButtonsPanel(screen));

        add(panel);
        setVisible(true);
    }

    public Calculator() {
        this("Calculator");
    }
}
