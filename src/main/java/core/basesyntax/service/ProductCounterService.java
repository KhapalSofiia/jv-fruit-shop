package core.basesyntax.service;
import core.basesyntax.db.Storage;
import java.util.Map;
public interface ProductCounterService {
    public void countTheProducts(String[][] report, Storage storage);
}
