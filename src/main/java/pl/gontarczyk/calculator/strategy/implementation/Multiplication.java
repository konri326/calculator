package pl.gontarczyk.calculator.strategy.implementation;

import org.springframework.stereotype.Component;
import pl.gontarczyk.calculator.strategy.CalculatorStrategy;

import java.math.BigDecimal;

@Component("*")
public class Multiplication implements CalculatorStrategy {

    @Override
    public BigDecimal calculate(BigDecimal firstValue, BigDecimal secondValue) {
        return firstValue.multiply(secondValue);
    }
}