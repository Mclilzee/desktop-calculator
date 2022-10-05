package project;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    public Calculator(String title) {
        super(title);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField field = new JTextField("2+2", 20);
        field.setMaximumSize(new Dimension(150, 25));
        field.setName("EquationTextField");
        field.setAlignmentX(CENTER_ALIGNMENT);

        JButton button = new JButton("Solve");
        button.setName("Solve");
        button.addActionListener(e -> field.setText("2+2=4"));
        button.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createGlue());
        add(field);
        add(Box.createGlue());
        add(button);
        add(Box.createGlue());

        setVisible(true);

    }
}
