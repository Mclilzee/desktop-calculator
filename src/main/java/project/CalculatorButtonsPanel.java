package project;

import javax.swing.*;
import java.awt.*;

public class CalculatorButtonsPanel extends JPanel {

    private final LogicProccessor processor;
    public CalculatorButtonsPanel(CalculatorScreen screen) {
        this.processor = new LogicProccessor(screen);

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

    public enum ButtonType {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        ZERO("0"),
        DOT("."),
        ADD("\u002B"),
        SUBTRACT("\u2212"),
        MULTIPLY("\u00D7"),
        DIVIDE("\u00F7"),
        EQUALS("="),
        CLEAR("C"),
        DELETE("Del");

        private final String value;

        ButtonType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
        }
    }

}
