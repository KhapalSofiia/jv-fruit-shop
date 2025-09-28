package core.basesyntax.strategy;
import core.basesyntax.db.Storage;
import core.basesyntax.exeptions.InvalidDataException;
public class ReturnActionTypeServiceImpl implements ActionTypeService {
    @Override
    public void applyTheQuantity (Storage storage, String product, int quantity) {
        if (quantity < 0) {
            throw new InvalidDataException("Quantity can't be negative " + quantity);
        }
        if (storage.contains(product)) {
            int currentQuantity = storage.get(product);
            storage.set(product, currentQuantity + quantity);
        } else {
            storage.set(product, quantity);
        }
    }
}
