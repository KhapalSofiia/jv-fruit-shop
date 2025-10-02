package core.basesyntax.exceptions;

public class FileDoesNotExistException extends RuntimeException {
    public FileDoesNotExistException(String fileName) {
        super(fileName);
    }

    public FileDoesNotExistException(String fileName, Throwable cause) {
        super(fileName, cause);
    }

    public FileDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
