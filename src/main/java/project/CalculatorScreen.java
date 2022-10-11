package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class CalculatorScreen extends JPanel {
    
    private final JLabel resultLabel;
    private final JLabel equationLabel;

    public CalculatorScreen() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setBorder(new EmptyBorder(0, 0, 20, 0));

        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        resultLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        resultLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        resultLabel.setText("0");

        equationLabel = new JLabel();
        equationLabel.setName("EquationLabel");
        equationLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        equationLabel.setForeground(Color.decode("#00b135"));
        equationLabel.setPreferredSize(new Dimension(0, 20));
        equationLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        add(resultLabel);
        add(equationLabel);
    }

    public void setResultLabelText(String text) {
        this.resultLabel.setText(text);
    }

    public void setEquationLabelText(String text) {
        this.equationLabel.setText(text);
    }
}
