package project;

import javax.swing.*;
import java.awt.*;

public class CalculatorButtonsPanel extends JPanel {

    public CalculatorButtonsPanel() {
        GridLayout layout = new GridLayout(5, 4, 10, 10);
        setLayout(layout);
        setAlignmentX(RIGHT_ALIGNMENT);

        add(Box.createGlue());
        add(Box.createGlue());
        add(new CalculatorButton("C", "Clear"));
        add(new CalculatorButton("Del", "Delete"));
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
        add(new CalculatorButton(".", "Dot"));
        add(new CalculatorButton("0", "Zero"));
        add(new CalculatorButton("=", "Equals"));
        add(new CalculatorButton("-", "Subtract"));
    }
}
