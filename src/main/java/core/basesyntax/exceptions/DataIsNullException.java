package core.basesyntax.exceptions;

public class DataIsNullException extends RuntimeException {
    public DataIsNullException(String message) {
        super(message);
    }

    public DataIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIsNullException(Throwable cause) {
        super(cause);
    }
}
