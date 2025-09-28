package core.basesyntax.exeptions;
public class FileDoesnExistsException extends RuntimeException {
    public FileDoesnExistsException(String fileName) {
        super(fileName);
    }
}
