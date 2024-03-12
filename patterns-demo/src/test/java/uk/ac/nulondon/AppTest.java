package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import uk.ac.nulondon.parser.Element;
import uk.ac.nulondon.parser.ParseException;
import uk.ac.nulondon.parser.SimpleCalculator;
import uk.ac.nulondon.visitor.Interpreter;

import java.util.Map;

public class AppTest {
    @Test
    void calculatorTest() throws ParseException {
        String script = "(-b - sqrt (b*b- 4*a*c)) / (2 * a)";
        Element expr = SimpleCalculator.parseScript(script);


        //Initialize the visitor and run trans
        Interpreter interpreter = new Interpreter(
                Map.of("a", 1.,
                        "b", 2024.,
                        "c", 2023.)
        );
        expr.accept(interpreter);
        Assertions.assertThat(interpreter.result()).isEqualTo(-2023.0);
    }
}
