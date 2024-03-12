package uk.ac.nulondon.parser;


import java.util.function.Consumer;

public record Number(String value) implements Element {

    public void accept(Consumer<? super Element> v) {
        v.accept(this);
    }
}
