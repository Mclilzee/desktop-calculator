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
    ADD("\u002B"),
    SUBTRACT("\u2212"),
    MULTIPLY("\u00D7"),
    DIVIDE("\u00F7"),
    EQUALS("="),
    CLEAR("C"),
    DELETE("Del");

    public final String VALUE;

    ButtonType(String value) {
        this.VALUE = value;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
    }
}
