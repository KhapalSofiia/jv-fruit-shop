package core.basesyntax.exeptions;
public class StorageIsNullException extends RuntimeException {
    public StorageIsNullException(String message) {
        super(message);
    }

    public StorageIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
