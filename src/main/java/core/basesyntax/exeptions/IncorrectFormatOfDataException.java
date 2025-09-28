package core.basesyntax.exeptions;
public class IncorrectFormatOfDataException extends RuntimeException {
    public IncorrectFormatOfDataException(String message) {
        super(message);
    }

    public IncorrectFormatOfDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFormatOfDataException(Throwable cause) {
        super(cause);
    }
}
