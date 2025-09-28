package core.basesyntax.exeptions;
public class UnknownActionException extends RuntimeException{
    public UnknownActionException(String message) {
        super(message);
    }
}
