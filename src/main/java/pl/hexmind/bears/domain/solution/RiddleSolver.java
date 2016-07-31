package pl.hexmind.bears.domain.solution;

import pl.hexmind.bears.domain.person.bear.BearAnswer;
import pl.hexmind.bears.domain.wardrobe.Hat;

import java.util.List;

public interface RiddleSolver {

    BearAnswer solve(List<Hat> visibleHats);
}
