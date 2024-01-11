package pl.gontarczyk.calculator.strategy.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.gontarczyk.calculator.exception.IllegalDivisionException;
import pl.gontarczyk.calculator.model.Equation;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionTest {

    private Division divisionStrategy;

    @BeforeEach
    void setup() {
        divisionStrategy = new Division();
    }

    @Test
    void testCalculate_shouldReturnResultOfDividedValues() {
        Equation equation = Equation.builder()
                .firstValue(new BigDecimal("14"))
                .secondValue(new BigDecimal("6"))
                .build();

        BigDecimal returnedResult = divisionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue());

        assertEquals(returnedResult, equation.getFirstValue().divide(equation.getSecondValue(), MathContext.DECIMAL64));
    }

    @Test
    void testCalculate_shouldThrowIllegalDivisionException() {
        Equation equation = Equation.builder()
                .firstValue(new BigDecimal("0"))
                .secondValue(new BigDecimal("6"))
                .build();
        String exceptionMsg = "You cannot divide by 0!";

        IllegalDivisionException exception = assertThrows(
                IllegalDivisionException.class,
                () -> divisionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue())
        );
        assertEquals(exception.getMessage(), exceptionMsg);
    }
}