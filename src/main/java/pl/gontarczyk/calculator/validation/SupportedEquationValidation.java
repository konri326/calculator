package pl.gontarczyk.calculator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class SupportedEquationValidation implements ConstraintValidator<SupportedEquation, String> {

    private final Pattern equationPattern = Pattern.compile("-?\\d*\\.?\\d* . -?\\d*\\.?\\d*");

    @Override
    public boolean isValid(String inputEquation, ConstraintValidatorContext constraintValidatorContext) {
        return equationPattern.matcher(inputEquation).matches();
    }
}