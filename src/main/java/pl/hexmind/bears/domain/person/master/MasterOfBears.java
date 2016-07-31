package pl.hexmind.bears.domain.person.master;

import com.google.common.base.MoreObjects;
import pl.hexmind.bears.domain.person.Person;
import pl.hexmind.bears.domain.person.Speech;
import pl.hexmind.bears.domain.person.bear.Bear;
import pl.hexmind.bears.domain.person.bear.ClothedBear;
import pl.hexmind.bears.domain.wardrobe.Hat;
import pl.hexmind.bears.domain.wardrobe.Wardrobe;
import pl.hexmind.bears.domain.wardrobe.WardrobeOpening;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.joining;

public class MasterOfBears implements Person {

    private final int power;
    private final Wardrobe wardrobe;
    private final Speech speech;

    /**
     * @param power    how many bears can summon
     * @param wardrobe has a lot of hats in different colors
     */
    public MasterOfBears(int power, Wardrobe wardrobe) {
        this.power = power;
        this.wardrobe = wardrobe;
        this.speech = new Speech(this);
    }

    public void say(String message) {
        speech.accept(message);
    }

    public List<Bear> summonBears() {
        return new BearsSummoning(power).summon();
    }

    public List<Hat> hasALotOfColorfulHats() {
        return new WardrobeOpening(wardrobe).getHats(power);
    }

    public void kill(Person bear) {
        say(bear + " is dead.");
    }

    public List<ClothedBear> putEverybodyHatOnHead(List<Bear> bears, List<Hat> hats) {
        checkArgument(bears.size() == hats.size(), "invalid number of hats/bears");
        Iterator<Bear> bearsSeq = bears.iterator();
        return hats.stream()
                .map(hat -> bearsSeq.next().withHat(hat))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("power", power)
                .toString();
    }

    public String thinkAboutHats(List<ClothedBear> bears) {
        String colors = bears.stream()
                .distinct()
                .map(bear -> bear.getHat().getColor().toString())
                .collect(joining(", "));
        return "(thinking) I will give them hats: " + colors + ".";
    }
}
