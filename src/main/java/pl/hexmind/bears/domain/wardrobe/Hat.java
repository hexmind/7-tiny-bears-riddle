package pl.hexmind.bears.domain.wardrobe;

import pl.hexmind.bears.domain.Color;

public class Hat {

    private final Color color;

    public Hat(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
