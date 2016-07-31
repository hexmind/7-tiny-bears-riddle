package pl.hexmind.bears;

import pl.hexmind.bears.domain.Color;
import pl.hexmind.bears.domain.person.bear.Bear;
import pl.hexmind.bears.domain.person.bear.BearAnswer;
import pl.hexmind.bears.domain.solution.RiddleSolver;
import pl.hexmind.bears.domain.wardrobe.Hat;

import java.util.List;

public class YourSolver implements RiddleSolver {

    private final Bear bear;

    public YourSolver(Bear bear) {
        this.bear = bear;
    }

    /**
     * TODO implement this method
     */
    @Override
    public BearAnswer solve(List<Hat> visibleHats) {
        Color guessed = null;
        return bear.answer(guessed);
    }
}
