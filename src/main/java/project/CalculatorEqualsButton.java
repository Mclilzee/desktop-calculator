package project;

public class CalculatorEqualsButton extends CalculatorButton {

    public CalculatorEqualsButton() {
        super("=", "Equals");
    }

    @Override
    public void performOperation() {
        CalculatorScreen.equalOperation();
    }
}
