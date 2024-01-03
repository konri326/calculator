package pl.gontarczyk.calculator.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EquationDto {

    private String equation;
    private String firstValue;
    private String operation;
    private String secondValue;
    private String result;
}