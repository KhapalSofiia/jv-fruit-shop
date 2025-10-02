package core.basesyntax.exceptions;

public class StrategyMapIsNullException extends RuntimeException {
    public StrategyMapIsNullException(String message) {
        super(message);
    }

    public StrategyMapIsNullException(Throwable cause) {
        super(cause);
    }

    public StrategyMapIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
