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
        add(new CalculatorButton("C", "Clear", ButtonType.Clear));
        add(new CalculatorButton("Del", "Delete", ButtonType.Delete));
        add(new CalculatorButton("7", "Seven", ButtonType.Number));
        add(new CalculatorButton("8", "Eight", ButtonType.Number));
        add(new CalculatorButton("9", "Nine", ButtonType.Number));
        add(new CalculatorButton("/", "Divide", ButtonType.Division));
        add(new CalculatorButton("4", "Four", ButtonType.Number));
        add(new CalculatorButton("5", "Five", ButtonType.Number));
        add(new CalculatorButton("6", "Six", ButtonType.Number));
        add(new CalculatorButton("x", "Multiply", ButtonType.Multiplication));
        add(new CalculatorButton("1", "One", ButtonType.Number));
        add(new CalculatorButton("2", "Two", ButtonType.Number));
        add(new CalculatorButton("3", "Three", ButtonType.Number));
        add(new CalculatorButton("+", "Add", ButtonType.Addition));
        add(new CalculatorButton(".", "Dot", ButtonType.Dot));
        add(new CalculatorButton("0", "Zero", ButtonType.Number));
        add(new CalculatorButton("=", "Equals", ButtonType.Equals));
        add(new CalculatorButton("-", "Subtract", ButtonType.Subtraction));
    }
}
