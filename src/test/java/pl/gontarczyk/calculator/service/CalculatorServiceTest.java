package pl.gontarczyk.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.gontarczyk.calculator.exception.StrategyNotFoundException;
import pl.gontarczyk.calculator.model.Equation;
import pl.gontarczyk.calculator.strategy.implementation.Addition;
import pl.gontarczyk.calculator.strategy.implementation.Division;
import pl.gontarczyk.calculator.strategy.implementation.Multiplication;
import pl.gontarczyk.calculator.strategy.implementation.Subtraction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    private Addition additionStrategy;

    @Mock
    private Division divisionStrategy;

    @Mock
    private Multiplication multiplicationStrategy;

    @Mock
    private Subtraction subtractionStrategy;

    @InjectMocks
    private CalculatorService calculatorService;

    @BeforeEach
    void setup() {
        calculatorService = new CalculatorService(Map.of(
                "+", additionStrategy,
                "/", divisionStrategy,
                "*", multiplicationStrategy,
                "-", subtractionStrategy));
    }

    @Test
    void testSolve_shouldReturnResultOfAddedValues() {
        Equation equation = createEquation("+");
        BigDecimal result = equation.getFirstValue().add(equation.getSecondValue());
        when(additionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue())).thenReturn(result);

        Equation resultEquation = calculatorService.solve(equation);

        assertEquals(resultEquation.getResult(), result);

        verify(additionStrategy, times(1)).calculate(equation.getFirstValue(), equation.getSecondValue());
    }

    @Test
    void testSolve_shouldReturnResultOfDividedValues() {
        Equation equation = createEquation("/");
        BigDecimal result = equation.getFirstValue().divide(equation.getSecondValue(), MathContext.DECIMAL64);
        when(divisionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue())).thenReturn(result);

        Equation resultEquation = calculatorService.solve(equation);

        assertEquals(resultEquation.getResult(), result);

        verify(divisionStrategy, times(1)).calculate(equation.getFirstValue(), equation.getSecondValue());
    }

    @Test
    void testSolve_shouldReturnResultOfMultipliedValues() {
        Equation equation = createEquation("*");
        BigDecimal result = equation.getFirstValue().multiply(equation.getSecondValue());
        when(multiplicationStrategy.calculate(equation.getFirstValue(), equation.getSecondValue())).thenReturn(result);

        Equation resultEquation = calculatorService.solve(equation);

        assertEquals(resultEquation.getResult(), result);

        verify(multiplicationStrategy, times(1)).calculate(equation.getFirstValue(), equation.getSecondValue());
    }

    @Test
    void testSolve_shouldReturnResultOfSubtractedValues() {
        Equation equation = createEquation("-");
        BigDecimal result = equation.getFirstValue().subtract(equation.getSecondValue());
        when(subtractionStrategy.calculate(equation.getFirstValue(), equation.getSecondValue())).thenReturn(result);

        Equation resultEquation = calculatorService.solve(equation);

        assertEquals(resultEquation.getResult(), result);

        verify(subtractionStrategy, times(1)).calculate(equation.getFirstValue(), equation.getSecondValue());
    }

    @Test
    void testSolve_shouldThrowStrategyNotFoundException() {
        Equation equation = createEquation("$");
        String exceptionMsg = "The calculator doesn't have this function!";

        StrategyNotFoundException exception = assertThrows(
                StrategyNotFoundException.class,
                () -> calculatorService.solve(equation)
        );
        assertEquals(exception.getMessage(), exceptionMsg);
    }

    private Equation createEquation(String inputOperation) {
        return Equation.builder()
                .firstValue(new BigDecimal("14"))
                .operation(inputOperation)
                .secondValue(new BigDecimal("6"))
                .build();
    }
}