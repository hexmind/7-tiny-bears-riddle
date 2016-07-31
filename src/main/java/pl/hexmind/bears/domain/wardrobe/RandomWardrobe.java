package pl.hexmind.bears.domain.wardrobe;

import pl.hexmind.bears.domain.Color;

import java.util.Random;
import java.util.stream.Stream;

public class RandomWardrobe implements Wardrobe {

    private int maxColors;

    public RandomWardrobe(int maxColors) {
        this.maxColors = maxColors;
    }

    @Override
    public Stream<Hat> streamHats() {
        return new Random().ints(0, maxColors)
                .boxed()
                .map(Color::valueOf)
                .map(Hat::new);
    }
}
