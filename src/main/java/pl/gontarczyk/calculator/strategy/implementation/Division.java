package pl.gontarczyk.calculator.strategy.implementation;

import org.springframework.stereotype.Component;
import pl.gontarczyk.calculator.strategy.CalculatorStrategy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component("/")
public class Division implements CalculatorStrategy {

    @Override
    public BigDecimal calculate(BigDecimal firstValue, BigDecimal secondValue) {
        if (!firstValue.equals(BigDecimal.ZERO) || !secondValue.equals(BigDecimal.ZERO)) {
            return firstValue.divide(secondValue, MathContext.DECIMAL64);
        }
        throw new IllegalArgumentException("");
    }
}