package pl.gontarczyk.calculator.strategy;

import java.math.BigDecimal;

public interface CalculatorStrategy {

    BigDecimal calculate(BigDecimal firstValue, BigDecimal secondValue);
}