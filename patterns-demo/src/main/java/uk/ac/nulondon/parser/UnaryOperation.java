package uk.ac.nulondon.parser;

import java.util.function.Consumer;

public record UnaryOperation(Operator operator, Element operand) implements Element {

    @Override
    public void accept(Consumer<? super Element> v) {
        operand.accept(v);
        v.accept(this);
    }

    public enum Operator {
        NEGATE('-'), SQUARE_ROOT('√');

        public char getSymbol() {
            return symbol;
        }

        final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }
    }

}
