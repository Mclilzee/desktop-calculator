package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculatorButtonsPanel extends JPanel {

    private final LogicProcessor processor;

    public CalculatorButtonsPanel(CalculatorScreen screen) {
        this.processor = new LogicProcessor(screen);

        GridLayout layout = new GridLayout(6, 4, 3, 3);
        setLayout(layout);
        setAlignmentX(RIGHT_ALIGNMENT);
        setBackground(Color.decode("#d3d3d3"));

        add(new CalculatorButton(ButtonType.PARENTHESES));
        add(new CalculatorButton(ButtonType.CLEAR_ENTRY));
        add(new CalculatorButton(ButtonType.CLEAR));
        add(new CalculatorButton(ButtonType.DELETE));

        add(new CalculatorButton(ButtonType.POWER_TWO));
        add(new CalculatorButton(ButtonType.POWER_Y));
        add(new CalculatorButton(ButtonType.SQUARE_ROOT));
        add(new CalculatorButton(ButtonType.DIVIDE));

        add(new CalculatorButton(ButtonType.SEVEN));
        add(new CalculatorButton(ButtonType.EIGHT));
        add(new CalculatorButton(ButtonType.NINE));
        add(new CalculatorButton(ButtonType.MULTIPLY));

        add(new CalculatorButton(ButtonType.FOUR));
        add(new CalculatorButton(ButtonType.FIVE));
        add(new CalculatorButton(ButtonType.SIX));
        add(new CalculatorButton(ButtonType.SUBTRACT));

        add(new CalculatorButton(ButtonType.ONE));
        add(new CalculatorButton(ButtonType.TWO));
        add(new CalculatorButton(ButtonType.THREE));
        add(new CalculatorButton(ButtonType.ADD));

        add(new CalculatorButton(ButtonType.PLUS_MINUS));
        add(new CalculatorButton(ButtonType.ZERO));
        add(new CalculatorButton(ButtonType.DOT));
        add(new CalculatorButton(ButtonType.EQUALS));
    }

    private class CalculatorButton extends JButton {

        public CalculatorButton(ButtonType type) {
            super(type.VALUE);
            setName(type.toString());
            setFocusPainted(false);
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            setBackGroundColor(type.VALUE);
            setBorder(new EmptyBorder(0,0,0,0));
            addActionListener(e -> processor.buttonPress(type));
        }

        private void setBackGroundColor(String value) {
            if (value.equals(ButtonType.PLUS_MINUS.VALUE) || value.matches("[\\d|.]")) {
                setBackground(Color.white);
            } else {
                setBackground(Color.decode("#dcdcdc"));
            }
        }
    }
}
