package Chess;

public enum Color {
    BLACK("BLACK"),
    WHITE("WHITE");

    private final String name;

    private Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
