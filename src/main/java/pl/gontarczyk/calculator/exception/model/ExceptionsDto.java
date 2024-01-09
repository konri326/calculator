package pl.gontarczyk.calculator.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class ExceptionsDto {

    private final Map<String, String> args = new HashMap<>();
    private final LocalDateTime dateTime = LocalDateTime.now();
}