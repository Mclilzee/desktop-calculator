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
        add(new CalculatorButton(ButtonType.CLEAR));
        add(new CalculatorButton(ButtonType.DELETE));
        add(new CalculatorButton(ButtonType.SEVEN));
        add(new CalculatorButton(ButtonType.EIGHT));
        add(new CalculatorButton(ButtonType.NINE));
        add(new CalculatorButton(ButtonType.DIVISION));
        add(new CalculatorButton(ButtonType.FOUR));
        add(new CalculatorButton(ButtonType.FIVE));
        add(new CalculatorButton(ButtonType.SIX));
        add(new CalculatorButton(ButtonType.MULTIPLICATION));
        add(new CalculatorButton(ButtonType.ONE));
        add(new CalculatorButton(ButtonType.TWO));
        add(new CalculatorButton(ButtonType.THREE));
        add(new CalculatorButton(ButtonType.ADDICTION));
        add(new CalculatorButton(ButtonType.DOT));
        add(new CalculatorButton(ButtonType.ZERO));
        add(new CalculatorButton(ButtonType.EQUALS));
        add(new CalculatorButton(ButtonType.SUBTRACTION));
    }
}
