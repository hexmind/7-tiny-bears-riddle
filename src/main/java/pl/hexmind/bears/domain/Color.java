package pl.hexmind.bears.domain;

public enum Color {

    WHITE, BLACK, RED, PINK, ORANGE, YELLOW, GREEN, MAGENTA, CYAN, BLUE, LIGHT_GRAY, GRAY, DARK_GRAY;

    @Override
    public String toString() {
        return name().toLowerCase().replaceAll("_", " ");
    }

    public static Color valueOf(int index) {
        return values()[index];
    }

}
