package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.DataIsNullException;
import core.basesyntax.exceptions.InvalidDataException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.strategy.ActionService;
import core.basesyntax.strategy.ActionTypeService;
import java.util.List;

public class ProductCounterServiceImpl implements ProductCounterService {
    private final ActionService actionService;

    public ProductCounterServiceImpl(ActionService actionService) {
        if (actionService == null) {
            throw new DataIsNullException("ActionService can't be null");
        }
        this.actionService = actionService;
    }

    @Override
    public void countTheProducts(List<FruitTransaction> fruitTransactions, Storage storage) {
        if (fruitTransactions == null) {
            throw new DataIsNullException("Report can't be null");
        }
        if (storage == null) {
            throw new DataIsNullException("Storage can't be null");
        }
        if (fruitTransactions.size() <= 1) {
            return;
        }
        for (FruitTransaction fruitTransaction : fruitTransactions) {
            if (fruitTransaction == null) {
                throw new DataIsNullException("Fruit transactions"
                        + "can't be null");
            }
            String productName = fruitTransaction.getProductName();
            int quantity = fruitTransaction.getQuantity();
            String actionCode = fruitTransaction.getActionCode();
            if (actionCode == null || productName == null) {
                throw new InvalidDataException("ActionCode"
                        + " and ProductName can't be null");
            }
            if (actionCode.isEmpty() || productName.isEmpty()) {
                throw new InvalidDataException(
                        "Fruit transaction: action/product must not be blank -> "
                                + fruitTransaction
                );
            }
            if (quantity < 0) {
                throw new InvalidDataException("Quantity must be "
                        + "greater than or equal to 0: " + quantity);
            }
            ActionTypeService action = actionService.getStrategy(actionCode);
            action.applyTheQuantity(storage, productName, quantity);
        }
    }
}
