package pl.gontarczyk.calculator.exception;

public class IllegalDivisionException extends RuntimeException {

    public IllegalDivisionException() {
        super("You cannot divide by 0!");
    }
}