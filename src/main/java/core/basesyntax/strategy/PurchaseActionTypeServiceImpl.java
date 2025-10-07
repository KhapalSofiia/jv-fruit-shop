package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.InvalidDataException;

public class PurchaseActionTypeServiceImpl implements ActionTypeService {
    @Override
    public void applyTheQuantity(Storage storage, String product, int quantity) {
        if (storage == null) {
            throw new InvalidDataException("Storage can't be null");
        }
        if (product == null || product.isBlank()) {
            throw new InvalidDataException("Product name is null or blank: " + product);
        }
        if (quantity < 0) {
            throw new InvalidDataException("Quantity can't be negative " + quantity);
        }
        Integer currentValue = storage.get(product);
        if (currentValue == null) {
            throw new InvalidDataException("Cannot purchase product '"
                    + product + "' because it's not in stock");
        }
        if (currentValue - quantity < 0) {
            throw new InvalidDataException("New quantity can't be negative: "
                    + (currentValue - quantity) + " for product: " + product);
        }
        storage.set(product, currentValue - quantity);
    }
}
