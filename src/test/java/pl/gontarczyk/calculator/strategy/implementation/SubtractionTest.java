package pl.gontarczyk.calculator.strategy.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.gontarczyk.calculator.model.Equation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubtractionTest {

    private Subtraction subtractionStrategy;

    @BeforeEach
    void setup() {
        subtractionStrategy = new Subtraction();
    }

    @Test
    void testCalculate_shouldReturnResultOfSubtractedValues() {
        Equation equation = Equation.builder()
                .firstValue(new BigDecimal("14"))
                .secondValue(new BigDecimal("6"))
                .build();

        BigDecimal returnedResult = subtractionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue());

        assertEquals(returnedResult, equation.getFirstValue().subtract(equation.getSecondValue()));
    }
}