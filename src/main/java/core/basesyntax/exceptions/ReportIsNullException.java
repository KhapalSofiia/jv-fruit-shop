package core.basesyntax.exceptions;

public class ReportIsNullException extends RuntimeException {
    public ReportIsNullException(String message) {
        super(message);
    }

    public ReportIsNullException(Throwable cause) {
        super(cause);
    }

    public ReportIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
