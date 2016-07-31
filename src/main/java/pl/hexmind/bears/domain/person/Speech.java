package pl.hexmind.bears.domain.person;

import java.util.function.Consumer;

public class Speech implements Consumer<String> {

    private Person person;

    public Speech(Person person) {
        this.person = person;
    }

    @Override
    public void accept(String message) {
        System.out.println(person + ": " + message);
    }
}
