package uk.ac.nulondon.parser;


import java.util.function.Consumer;

public record BinaryOperation(Operator operator, Element left, Element right)
        implements Element {

    @Override
    public void accept(Consumer<? super Element> v) {
        left.accept(v);
        right.accept(v);
        v.accept(this);
    }

    public enum Operator {
        ADD('+'), SUBTRACT('−'), MULTIPLY('×'), DIVIDE('÷');

        public char getSymbol() {
            return symbol;
        }

        private final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }
    }

}
