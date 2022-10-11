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
    ADDICTION("\u002B"),
    SUBTRACTION("\u2212"),
    MULTIPLICATION("\u00D7"),
    DIVISION("\u00F7"),
    EQUALS("="),
    CLEAR("C"),
    DELETE("Del");

    private final String value;

    ButtonType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
    }
}
