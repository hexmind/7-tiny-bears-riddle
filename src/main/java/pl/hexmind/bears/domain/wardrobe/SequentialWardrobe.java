package pl.hexmind.bears.domain.wardrobe;

import com.google.common.collect.Iterables;
import pl.hexmind.bears.domain.Color;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SequentialWardrobe implements Wardrobe {

    private final Iterable<Integer> cycle;

    public SequentialWardrobe(List<Integer> sequence) {
        this.cycle = Iterables.cycle(sequence);
    }

    @Override
    public Stream<Hat> streamHats() {
        return StreamSupport.stream(cycle.spliterator(), false)
                .map(Color::valueOf)
                .map(Hat::new);
    }
}
