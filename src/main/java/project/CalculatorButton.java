package project;

import javax.swing.*;

public class CalculatorButton extends JButton {

    public CalculatorButton(ButtonType type) {
        super(type.getValue());
        setName(type.name());
        setFocusPainted(false);
        addActionListener(e -> System.out.println(type));
    }
}
