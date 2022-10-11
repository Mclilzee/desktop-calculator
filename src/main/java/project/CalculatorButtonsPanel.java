package project;

import javax.swing.*;
import java.awt.*;

public class CalculatorButtonsPanel extends JPanel {

    private final LogicProcessor processor;
    public CalculatorButtonsPanel(CalculatorScreen screen) {
        this.processor = new LogicProcessor(screen);

        GridLayout layout = new GridLayout(5, 4, 10, 10);
        setLayout(layout);
        setAlignmentX(RIGHT_ALIGNMENT);

        add(Box.createGlue());
        add(Box.createGlue());
        add(new CalculatorButton(ButtonType.CLEAR));
        add(new CalculatorButton(ButtonType.DELETE));
        add(new CalculatorButton(ButtonType.SEVEN));
        add(new CalculatorButton(ButtonType.EIGHT));
        add(new CalculatorButton(ButtonType.NINE));
        add(new CalculatorButton(ButtonType.DIVIDE));
        add(new CalculatorButton(ButtonType.FOUR));
        add(new CalculatorButton(ButtonType.FIVE));
        add(new CalculatorButton(ButtonType.SIX));
        add(new CalculatorButton(ButtonType.MULTIPLY));
        add(new CalculatorButton(ButtonType.ONE));
        add(new CalculatorButton(ButtonType.TWO));
        add(new CalculatorButton(ButtonType.THREE));
        add(new CalculatorButton(ButtonType.ADD));
        add(new CalculatorButton(ButtonType.DOT));
        add(new CalculatorButton(ButtonType.ZERO));
        add(new CalculatorButton(ButtonType.EQUALS));
        add(new CalculatorButton(ButtonType.SUBTRACT));
    }

    private class CalculatorButton extends JButton {

        public CalculatorButton(ButtonType type) {
            super(type.getValue());
            setName(type.name());
            setFocusPainted(false);
            addActionListener(e -> processor.buttonPress(type));
        }
    }

}
