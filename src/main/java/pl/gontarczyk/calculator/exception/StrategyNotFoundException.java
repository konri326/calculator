package pl.gontarczyk.calculator.exception;

public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException() {
        super("The calculator doesn't have this function!");
    }
}