package pl.gontarczyk.calculator.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Builder
public class ExceptionDto {

    private final String message;
    private final LocalDateTime dateTime = LocalDateTime.now();
}