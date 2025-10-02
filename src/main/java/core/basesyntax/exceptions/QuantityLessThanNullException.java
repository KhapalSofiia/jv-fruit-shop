package core.basesyntax.exceptions;

public class QuantityLessThanNullException extends RuntimeException {
    public QuantityLessThanNullException(String message) {
        super(message);
    }

    public QuantityLessThanNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuantityLessThanNullException(Throwable cause) {
        super(cause);
    }
}
