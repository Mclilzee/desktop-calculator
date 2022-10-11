package project;

import javax.swing.*;

public class CalculatorButton extends JButton {

    public CalculatorButton(ButtonType type) {
        super(type.getValue());
        setFocusPainted(false);
        addActionListener(e -> System.out.println(type));
    }
}
