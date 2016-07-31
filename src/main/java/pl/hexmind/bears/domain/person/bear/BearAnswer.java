package pl.hexmind.bears.domain.person.bear;

import pl.hexmind.bears.domain.Color;

public class BearAnswer {

    private final Bear bear;
    private final Color color;

    public BearAnswer(Bear bear, Color color) {
        this.bear = bear;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Bear getBear() {
        return bear;
    }
}
