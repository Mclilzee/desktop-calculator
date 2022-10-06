package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorButtonsPanel extends JPanel {
    
    public CalculatorButtonsPanel() {
        GridLayout layout = new GridLayout(4, 4);
        layout.setHgap(20);
        layout.setVgap(20);
        setLayout(layout);
        setBorder(new EmptyBorder(0, 20, 20, 20));

        add(new CalculatorButton("7", "Seven"));
        add(new CalculatorButton("8", "Eight"));
        add(new CalculatorButton("9", "Nine"));
        add(new CalculatorButton("/", "Divide"));
        add(new CalculatorButton("4", "Four"));
        add(new CalculatorButton("5", "Five"));
        add(new CalculatorButton("6", "Six"));
        add(new CalculatorButton("x", "Multiply"));
        add(new CalculatorButton("1", "One"));
        add(new CalculatorButton("2", "Two"));
        add(new CalculatorButton("3", "Three"));
        add(new CalculatorButton("+", "Add"));
        add(Box.createGlue());
        add(new CalculatorButton("0", "Zero"));
        add(new CalculatorButton("=", "Equals"));
        add(new CalculatorButton("-", "Subtract"));
    }
}
