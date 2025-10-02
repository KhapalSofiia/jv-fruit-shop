package core.basesyntax.exceptions;

public class ReadNotPossibleException extends RuntimeException {
    public ReadNotPossibleException(String fileName) {
        super(fileName);
    }

    public ReadNotPossibleException(Throwable cause) {
        super(cause);
    }

    public ReadNotPossibleException(String fileName, Throwable cause) {
        super(fileName, cause);
    }
}
