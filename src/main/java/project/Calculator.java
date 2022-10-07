package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator extends JFrame {

    public Calculator(String title) {
        super(title);
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(20, 15));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(CalculatorScreen.getTextField(), BorderLayout.NORTH);
        panel.add(new CalculatorButtonsPanel(), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
