package core.basesyntax.exceptions;

public class StrategyIsNullException extends RuntimeException {
    public StrategyIsNullException(String message) {
        super(message);
    }

    public StrategyIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrategyIsNullException(Throwable cause) {
        super(cause);
    }
}
