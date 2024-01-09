package pl.gontarczyk.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.gontarczyk.calculator.exception.model.ExceptionDto;
import pl.gontarczyk.calculator.exception.model.ExceptionsDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionsDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ExceptionsDto exceptionsDto = new ExceptionsDto();
        ex.getFieldErrors()
                .forEach(error -> exceptionsDto.getArgs().put(error.getField(), error.getDefaultMessage()));
        return exceptionsDto;
    }

    @ExceptionHandler(StrategyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto strategyNotFoundExceptionHandler(StrategyNotFoundException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ExceptionHandler(IllegalDivisionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto illegalDivisionExceptionHandler(IllegalDivisionException ex) {
        return new ExceptionDto(ex.getMessage());
    }
}