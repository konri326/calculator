package pl.gontarczyk.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gontarczyk.calculator.mapper.EquationMapper;
import pl.gontarczyk.calculator.model.EquationDto;
import pl.gontarczyk.calculator.model.EquationRequestDto;
import pl.gontarczyk.calculator.service.CalculatorService;

@RestController
@RequestMapping("/api/v1/equation")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final EquationMapper equationMapper;

    @PostMapping("/solve")
    public ResponseEntity<EquationDto> solve(@RequestBody EquationRequestDto equationRequestDto) {
        EquationDto equationDto = equationMapper.fromRequestDto(equationRequestDto);
        EquationDto returnedEquation = equationMapper.toDto(calculatorService.solve(equationMapper.toEntity(equationDto)));
        return new ResponseEntity<>(returnedEquation, HttpStatus.OK);
    }
}