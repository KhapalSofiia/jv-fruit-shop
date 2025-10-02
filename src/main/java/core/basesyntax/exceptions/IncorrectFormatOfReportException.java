package core.basesyntax.exceptions;

public class IncorrectFormatOfReportException extends RuntimeException {
    public IncorrectFormatOfReportException(String message) {
        super(message);
    }

    public IncorrectFormatOfReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFormatOfReportException(Throwable cause) {
        super(cause);
    }
}
