package project;

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
    ADD("\u002B", 1),
    SUBTRACT("\u2212", 1),
    MULTIPLY("\u00D7", 2),
    DIVIDE("\u00F7", 2),
    EQUALS("="),
    CLEAR("C"),
    CLEAR_ENTRY("CE"),
    DELETE("Del"),
    SQUARE_ROOT("\u221A", 3),
    POWER("^", 3),
    POWER_TWO("\u03C7" + "\u00B2"),
    POWER_Y("\u03C7" + "\u02B8"),
    PLUS_MINUS("\u00B1"),
    OPEN_PARENTHESES("(", 4),
    CLOSED_PARENTHESES(")", 4),
    PARENTHESES(OPEN_PARENTHESES.VALUE + " " + CLOSED_PARENTHESES.VALUE);

    public final String VALUE;

    public final int PRECEDENCE;

    ButtonType(String value, int precedence) {
        this.VALUE = value;
        this.PRECEDENCE = precedence;
    }

    ButtonType(String value) {
        this(value, 0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[] strings = super.toString().split("_");

        for (String each : strings) {
            builder.append(capitalize(each));
        }

        return builder.toString();
    }

    private String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
