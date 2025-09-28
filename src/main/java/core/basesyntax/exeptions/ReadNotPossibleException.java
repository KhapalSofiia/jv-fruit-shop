package core.basesyntax.exeptions;
public class ReadNotPossibleException extends RuntimeException {
    public ReadNotPossibleException(String fileName) {
        super(fileName);
    }
}
