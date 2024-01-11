package pl.gontarczyk.calculator.strategy.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.gontarczyk.calculator.model.Equation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplicationTest {

    private Multiplication multiplicationStrategy;

    @BeforeEach
    void setup() {
        multiplicationStrategy = new Multiplication();
    }

    @Test
    void testCalculate_shouldReturnResultOfMultipliedValues() {
        Equation equation = Equation.builder()
                .firstValue(new BigDecimal("2.53"))
                .secondValue(new BigDecimal("2.22"))
                .build();

        BigDecimal returnedResult = multiplicationStrategy.calculate(equation.getFirstValue(), equation.getSecondValue());

        assertEquals(returnedResult, equation.getFirstValue().multiply(equation.getSecondValue()));
    }
}