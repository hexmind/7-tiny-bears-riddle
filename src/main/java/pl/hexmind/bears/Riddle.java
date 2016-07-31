package pl.hexmind.bears;

import com.google.common.base.Throwables;
import pl.hexmind.bears.domain.Color;
import pl.hexmind.bears.domain.person.bear.Bear;
import pl.hexmind.bears.domain.person.bear.BearAnswer;
import pl.hexmind.bears.domain.person.bear.ClothedBear;
import pl.hexmind.bears.domain.person.master.MasterOfBears;
import pl.hexmind.bears.domain.solution.ExistingSolver;
import pl.hexmind.bears.domain.solution.RiddleSolver;
import pl.hexmind.bears.domain.wardrobe.Hat;
import pl.hexmind.bears.domain.wardrobe.RandomWardrobe;
import pl.hexmind.bears.domain.wardrobe.Wardrobe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class execute one of riddle solvers: your or our solution. Remember, your solver is ignored until it is not implemented.
 * You should implement your solution in YourSolver.java
 *
 * @see pl.hexmind.bears.YourSolver#solve
 * @see pl.hexmind.bears.RiddleSpec (in test module)
 */
public class Riddle {

    private static final int BEARS_NUMBER = 7;

    private final MasterOfBears master;

    public Riddle(MasterOfBears master) {
        this.master = master;
    }

    public static void main(String[] args) {
        Wardrobe wardrobe = new RandomWardrobe(BEARS_NUMBER);
        MasterOfBears master = new MasterOfBears(BEARS_NUMBER, wardrobe);
        new Riddle(master).start();
    }

    public void start() {
        List<Bear> bears = master.summonBears();
        try {
            playGame(master, bears);
            master.say("Congratulations my dear bears.");
        } catch (IllegalStateException e) {
            bears.stream().forEach(master::kill);
            master.say("I'm sorry but " + e.getMessage());
            Throwables.propagate(e);
        }
    }

    private void playGame(MasterOfBears master, List<Bear> bears) {
        List<Hat> hats = master.hasALotOfColorfulHats();
        List<ClothedBear> clothed = master.putEverybodyHatOnHead(bears, hats);
        master.say(master.thinkAboutHats(clothed));
        clothed.stream()
                .map(bear -> guessHatsColors(bear, clothed))
                .filter(answer -> verify(answer, clothed))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("None of bears is right!"));
    }

    private BearAnswer guessHatsColors(ClothedBear clothedBear, List<ClothedBear> all) {
        Bear bear = clothedBear.withoutHat();
        List<Hat> visibleByBear = onlyVisibleHats(clothedBear, all);
        RiddleSolver yourSolver = new YourSolver(bear);
        RiddleSolver ourSolver = new ExistingSolver(bear);
        return Stream.of(yourSolver, ourSolver)
                .map(solver -> solver.solve(visibleByBear))
                .filter(answer -> answer != null && answer.getColor() != null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("You can modify only YourSolver class"));
    }

    private List<Hat> onlyVisibleHats(ClothedBear bear, List<ClothedBear> bears) {
        return bears.stream()
                .filter(b -> b != bear)
                .map(ClothedBear::getHat)
                .collect(Collectors.toList());
    }

    private boolean verify(BearAnswer answer, List<ClothedBear> bears) {
        ClothedBear bearWithHat = answer.getBear().findYourself(bears);
        Color guessed = answer.getColor();
        bearWithHat.say("Maybe I have " + guessed + " hat?");
        return bearWithHat.getHat().getColor() == guessed;
    }

}
