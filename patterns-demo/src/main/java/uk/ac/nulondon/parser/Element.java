package uk.ac.nulondon.parser;

import java.util.function.Consumer;

public sealed interface Element permits
        BinaryOperation,
        UnaryOperation,
        Number,
        Variable {
    void accept(Consumer<? super Element> v);
}


