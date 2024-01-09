package pl.gontarczyk.calculator.model;

import lombok.*;
import pl.gontarczyk.calculator.validation.SupportedEquation;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EquationRequestDto {

    @SupportedEquation
    private String equation;
}