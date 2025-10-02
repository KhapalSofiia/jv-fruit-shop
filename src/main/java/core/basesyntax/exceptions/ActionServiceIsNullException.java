package core.basesyntax.exceptions;

public class ActionServiceIsNullException extends RuntimeException {
    public ActionServiceIsNullException(String message) {
        super(message);
    }

    public ActionServiceIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionServiceIsNullException(Throwable cause) {
        super(cause);
    }
}
