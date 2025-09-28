package core.basesyntax.exeptions;
import java.io.IOException;
public class FileWriteException extends RuntimeException {
    public FileWriteException(String message, IOException e) {
        super(message);
    }

    public FileWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileWriteException(Throwable cause) {
        super(cause);
    }
}
