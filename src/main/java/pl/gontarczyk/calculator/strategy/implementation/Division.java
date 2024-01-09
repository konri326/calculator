package pl.gontarczyk.calculator.strategy.implementation;

import org.springframework.stereotype.Component;
import pl.gontarczyk.calculator.exception.IllegalDivisionException;
import pl.gontarczyk.calculator.strategy.CalculatorStrategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component("/")
public class Division implements CalculatorStrategy {

    @Override
    public BigDecimal calculate(BigDecimal firstValue, BigDecimal secondValue) {
        if (firstValue.equals(BigDecimal.ZERO) || secondValue.equals(BigDecimal.ZERO)) {
            throw new IllegalDivisionException();
        }
        return firstValue.divide(secondValue, MathContext.DECIMAL64);
    }
}