package pl.gontarczyk.calculator.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Builder
public class ExceptionsDto {

    private final Map<String, String> args = new HashMap<>();
    private final LocalDateTime dateTime = LocalDateTime.now();
}