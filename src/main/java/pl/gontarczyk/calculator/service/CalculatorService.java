package pl.gontarczyk.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gontarczyk.calculator.model.Equation;
import pl.gontarczyk.calculator.strategy.CalculatorStrategy;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final Map<String, CalculatorStrategy> strategies;

    public Equation solve(Equation equation) {
        CalculatorStrategy strategy = strategies.get(equation.getOperation());
        equation.setResult(strategy.calculate(equation.getFirstValue(), equation.getSecondValue()));
        return equation;
    }
}