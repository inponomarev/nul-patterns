package uk.ac.nulondon.visitor;

import uk.ac.nulondon.parser.BinaryOperation;
import uk.ac.nulondon.parser.Element;
import uk.ac.nulondon.parser.Number;
import uk.ac.nulondon.parser.UnaryOperation;
import uk.ac.nulondon.parser.Variable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.Consumer;

public final class Interpreter implements Consumer<Element> {
    private final Map<String, Double> variables;
    Deque<Double> stack = new ArrayDeque<>();

    public Interpreter(Map<String, Double> variables) {
        this.variables = variables;
    }

    void visit(BinaryOperation e) {
        double right = stack.pop();
        double left = stack.pop();
        stack.push(switch (e.operator()) {
            case ADD -> left + right;
            case SUBTRACT -> left - right;
            case MULTIPLY -> left * right;
            case DIVIDE -> left / right;
        });
    }

    void visit(UnaryOperation e) {
        double arg = stack.pop();
        stack.push(switch (e.operator()) {
            case NEGATE -> -arg;
            case SQUARE_ROOT -> Math.sqrt(arg);
        });
    }

    void visit(Number e) {
        stack.push(Double.parseDouble(e.value()));
    }

    void visit(Variable e) {
        Double value = variables.get(e.name());
        if (value == null) throw new IllegalStateException("Variable " + e.name() + "not defined");
        stack.push(value);
    }

    public double result() {
        return stack.pop();
    }

    @Override
    public void accept(Element expr) {
        //Using switch pattern matching to call the right version of "visit"
        switch (expr) {
            case BinaryOperation e -> visit(e);
            case UnaryOperation e -> visit(e);
            case Number e -> visit(e);
            case Variable e -> visit(e);
        }
    }
}
