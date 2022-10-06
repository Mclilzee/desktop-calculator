package project;

import javax.swing.*;

public abstract class CalculatorButton extends JButton {

    public CalculatorButton(String text, String name) {
        super(text);
        this.setName(name);
    }

    abstract void performOperation();
}
