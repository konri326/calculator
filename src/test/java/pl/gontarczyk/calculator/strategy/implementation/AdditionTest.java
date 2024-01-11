package pl.gontarczyk.calculator.strategy.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.gontarczyk.calculator.model.Equation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {

    private Addition additionStrategy;

    @BeforeEach
    void setup() {
        additionStrategy = new Addition();
    }

    @Test
    void testCalculate_shouldReturnResultOfAddedValues() {
        Equation equation = Equation.builder()
                .firstValue(new BigDecimal("2.53"))
                .secondValue(new BigDecimal("2.22"))
                .build();

        BigDecimal returnedResult = additionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue());

        assertEquals(returnedResult, equation.getFirstValue().add(equation.getSecondValue()));
    }
}