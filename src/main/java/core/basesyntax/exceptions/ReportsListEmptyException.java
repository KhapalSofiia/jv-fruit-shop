package core.basesyntax.exceptions;

public class ReportsListEmptyException extends RuntimeException {
    public ReportsListEmptyException(String message) {
        super(message);
    }

    public ReportsListEmptyException(Throwable cause) {
        super(cause);
    }

    public ReportsListEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
