package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator extends JFrame {

    public Calculator(String title) {
        super(title);
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#d3d3d3"));

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        CalculatorScreen screen = new CalculatorScreen();
        panel.add(screen);

        CalculatorButtonsPanel buttonsPanel = new CalculatorButtonsPanel(screen);
        panel.add(buttonsPanel);
        addKeyListener(buttonsPanel.getListener());

        add(panel);
        setVisible(true);
    }

    public Calculator() {
        this("Calculator");
    }
}
