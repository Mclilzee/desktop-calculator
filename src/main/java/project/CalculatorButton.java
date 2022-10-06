package project;

import javax.swing.*;

public class CalculatorButton extends JButton {

    public CalculatorButton(String text, String name) {
        super(text);
        this.setName(name);
    }

    public void performOperation() {
        addActionListener(e -> {
            CalculatorScreen.buttonPress(getText());
        });
    }
}
