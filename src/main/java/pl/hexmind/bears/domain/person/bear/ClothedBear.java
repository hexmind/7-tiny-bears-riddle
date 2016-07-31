package pl.hexmind.bears.domain.person.bear;

import com.google.common.base.MoreObjects;
import pl.hexmind.bears.domain.person.Person;
import pl.hexmind.bears.domain.person.Speech;
import pl.hexmind.bears.domain.wardrobe.Hat;

public class ClothedBear implements Person {

    private final Bear bear;
    private final Hat hat;
    private final Speech speech;

    public ClothedBear(Bear bear, Hat hat) {
        this.bear = bear;
        this.hat = hat;
        this.speech = new Speech(this);
    }

    public Bear withoutHat() {
        return new Bear(bear.getId());
    }

    public void say(String message) {
        speech.accept(message);
    }

    public long getId() {
        return bear.getId();
    }

    public Hat getHat() {
        return hat;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("hat", hat)
                .toString();
    }
}
