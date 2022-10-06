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
        add(new CalculatorScreen(getWidth(), 100));
        add(new CalculatorButtonsPanel());

        setVisible(true);
    }
}
