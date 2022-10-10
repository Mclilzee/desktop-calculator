package project;

import javax.swing.*;

public class CalculatorButton extends JButton {

    public CalculatorButton(String text, String name, ButtonType type) {
        super(text);
        this.setName(name);
        setFocusPainted(false);
        addActionListener(e -> CalculatorScreen.buttonPress(type, getText()));
    }
}
