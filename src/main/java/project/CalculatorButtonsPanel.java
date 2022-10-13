package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculatorButtonsPanel extends JPanel {

    private final LogicProcessor processor;
    private final ButtonsKeyListener listener;
    private final Map<String, CalculatorButton> buttonsMap;

    public CalculatorButtonsPanel(CalculatorScreen screen) {
        this.processor = new LogicProcessor(screen);
        listener = new ButtonsKeyListener();
        buttonsMap = new LinkedHashMap<>();

        GridLayout layout = new GridLayout(6, 4, 3, 3);
        setLayout(layout);
        setAlignmentX(RIGHT_ALIGNMENT);
        setBackground(Color.decode("#d3d3d3"));

        buttonsMap.put("(", new CalculatorButton(ButtonType.PARENTHESES));
        buttonsMap.put("CE", new CalculatorButton(ButtonType.CLEAR_ENTRY));
        buttonsMap.put("C", new CalculatorButton(ButtonType.CLEAR));
        buttonsMap.put("Delete", new CalculatorButton(ButtonType.DELETE));

        buttonsMap.put("^2", new CalculatorButton(ButtonType.POWER_TWO));
        buttonsMap.put("^", new CalculatorButton(ButtonType.POWER_Y));
        buttonsMap.put("sqrt", new CalculatorButton(ButtonType.SQUARE_ROOT));
        buttonsMap.put("/", new CalculatorButton(ButtonType.DIVIDE));

        buttonsMap.put("7", new CalculatorButton(ButtonType.SEVEN));
        buttonsMap.put("8", new CalculatorButton(ButtonType.EIGHT));
        buttonsMap.put("9", new CalculatorButton(ButtonType.NINE));
        buttonsMap.put("*", new CalculatorButton(ButtonType.MULTIPLY));

        buttonsMap.put("4", new CalculatorButton(ButtonType.FOUR));
        buttonsMap.put("5", new CalculatorButton(ButtonType.FIVE));
        buttonsMap.put("6", new CalculatorButton(ButtonType.SIX));
        buttonsMap.put("-", new CalculatorButton(ButtonType.SUBTRACT));

        buttonsMap.put("1", new CalculatorButton(ButtonType.ONE));
        buttonsMap.put("2", new CalculatorButton(ButtonType.TWO));
        buttonsMap.put("3", new CalculatorButton(ButtonType.THREE));
        buttonsMap.put("+", new CalculatorButton(ButtonType.ADD));

        buttonsMap.put("+-", new CalculatorButton(ButtonType.PLUS_MINUS));
        buttonsMap.put("0", new CalculatorButton(ButtonType.ZERO));
        buttonsMap.put(".", new CalculatorButton(ButtonType.DOT));
        buttonsMap.put("=", new CalculatorButton(ButtonType.EQUALS));

        fillPanelWithButtons();
    }

    private void fillPanelWithButtons() {
        for (CalculatorButton button : buttonsMap.values()) {
            add(button);
        }
    }

    public ButtonsKeyListener getListener() {
        return listener;
    }

    private class CalculatorButton extends JButton {

        public CalculatorButton(ButtonType type) {
            super(type.VALUE);
            setName(type.toString());
            setFocusPainted(false);
            setFocusable(false);
            setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            setBackGroundColor(type.VALUE);
            setBorder(new EmptyBorder(0, 0, 0, 0));
            addActionListener(e -> processor.buttonPress(type));
        }

        private void setBackGroundColor(String value) {
            if (value.equals(ButtonType.PLUS_MINUS.VALUE) || value.matches("[\\d|.]")) {
                setBackground(Color.white);
            } else {
                setBackground(Color.decode("#dcdcdc"));
            }
        }
    }

    private class ButtonsKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            clickButton(e.getKeyChar());
        }

        private void clickButton(char type) {
            switch (type) {
                case '.':
                    buttonsMap.get(".").doClick();
                    break;
                case '*':
                    buttonsMap.get("*").doClick();
                    break;
                case '/':
                    buttonsMap.get("/").doClick();
                    break;
                case '+':
                    buttonsMap.get("+").doClick();
                    break;
                case '-':
                    buttonsMap.get("-").doClick();
                    break;
                case '\u001B':
                    buttonsMap.get("CE").doClick();
                    break;
                case '(':
                case ')':
                    buttonsMap.get("(").doClick();
                    break;
                case '^':
                    buttonsMap.get("^").doClick();
                    break;
                case '\b':
                    buttonsMap.get("Delete").doClick();
                    break;
                case '\n':
                    buttonsMap.get("=").doClick();
                    break;
                default:
                    clickNumber(type);
                    break;
            }
        }

        private void clickNumber(char type) {
            CalculatorButton button = buttonsMap.get(String.valueOf(type));
            if (button != null) {
                button.doClick();
            }
        }
    }
}
