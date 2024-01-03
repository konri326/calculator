package pl.gontarczyk.calculator.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Equation {

    private String equation;
    private BigDecimal firstValue;
    private char operation;
    private BigDecimal secondValue;
    private BigDecimal result;
}