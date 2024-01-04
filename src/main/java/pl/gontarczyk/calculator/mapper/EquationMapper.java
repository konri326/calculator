package pl.gontarczyk.calculator.mapper;

import org.mapstruct.Mapper;
import pl.gontarczyk.calculator.model.Equation;
import pl.gontarczyk.calculator.model.EquationDto;
import pl.gontarczyk.calculator.model.EquationRequestDto;

import java.util.Scanner;

@Mapper
public interface EquationMapper {

    default EquationDto fromRequestDto(EquationRequestDto equationRequestDto) {
        Scanner scanner = new Scanner(equationRequestDto.getEquation());
        scanner.useDelimiter(" ");
        return EquationDto.builder()
                .equation(equationRequestDto.getEquation())
                .firstValue(scanner.next())
                .operation(scanner.next())
                .secondValue(scanner.next())
                .build();
    }

    Equation toEntity(EquationDto equationDto);

    EquationDto toDto(Equation equation);
}