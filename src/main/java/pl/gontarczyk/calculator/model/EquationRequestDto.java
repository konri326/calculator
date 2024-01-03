package pl.gontarczyk.calculator.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EquationRequestDto {

    private String equation;
}