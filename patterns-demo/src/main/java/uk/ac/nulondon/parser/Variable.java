package uk.ac.nulondon.parser;

import java.util.function.Consumer;

public record Variable(String name) implements Element {

    @Override
    public void accept(Consumer<? super Element> v) {
        v.accept(this);
    }
}
