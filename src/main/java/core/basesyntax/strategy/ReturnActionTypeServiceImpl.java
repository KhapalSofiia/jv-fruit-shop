package core.basesyntax.strategy;

import core.basesyntax.exeptions.InvalidDataException;

public class ReturnActionTypeServiceImpl implements ActionTypeService {
    @Override
    public int getTheAction(int currentQuantity, int quantity) {
        if (currentQuantity < 0) {
            throw new InvalidDataException("Current quantity can't be negative "
                    + currentQuantity);
        }
        if (quantity < 0) {
            throw new InvalidDataException("Quantity can't be negative " + quantity);
        }
        return currentQuantity + quantity;
    }
}
