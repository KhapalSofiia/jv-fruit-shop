package core.basesyntax.exceptions;

public class ReportsListNullException extends RuntimeException {
    public ReportsListNullException(String message) {
        super(message);
    }

    public ReportsListNullException(Throwable cause) {
        super(cause);
    }

    public ReportsListNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
