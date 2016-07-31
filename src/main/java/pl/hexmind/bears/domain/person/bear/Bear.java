package pl.hexmind.bears.domain.person.bear;

import com.google.common.base.MoreObjects;
import pl.hexmind.bears.domain.Color;
import pl.hexmind.bears.domain.person.Person;
import pl.hexmind.bears.domain.person.Speech;
import pl.hexmind.bears.domain.wardrobe.Hat;

import java.util.List;

public class Bear implements Person {

    private final long id;
    private final Speech speech;

    public Bear(long id) {
        this.id = id;
        this.speech = new Speech(this);
    }

    public void say(String message) {
        speech.accept(message);
    }

    public long getId() {
        return id;
    }

    public ClothedBear withHat(Hat hat) {
        return new ClothedBear(this, hat);
    }


    public ClothedBear findYourself(List<ClothedBear> bears) {
        return bears.stream()
                .filter(bear -> bear.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(this + " doesn't belong to bears"));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }

    public BearAnswer answer(Color guessed) {
        return new BearAnswer(this, guessed);
    }
}
