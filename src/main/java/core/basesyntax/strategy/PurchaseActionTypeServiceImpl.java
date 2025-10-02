package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.InvalidDataException;
import core.basesyntax.exceptions.QuantityLessThanNullException;

public class PurchaseActionTypeServiceImpl implements ActionTypeService {
    @Override
    public void applyTheQuantity (Storage storage, String product, int quantity) {
        if (quantity < 0) {
            throw new InvalidDataException("Quantity can't be negative " + quantity);
        }
        if (storage.contains(product)) {
            int currentQuantity = storage.get(product);
            if (quantity > currentQuantity) {
                throw new InvalidDataException("Purchase quantity (" + quantity
                        + ") exceeds current stock (" + currentQuantity + ")"
                );
            }
            if (currentQuantity - quantity < 0) {
                throw new QuantityLessThanNullException("New cuantity can't be negative: " + (currentQuantity - quantity));
            }
            storage.set(product, currentQuantity - quantity);
        } else {
            throw new InvalidDataException("Cannot purchase product '" + product + "' because it's not in stock");
        }
    }
}