package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.ActionServiceIsNullException;
import core.basesyntax.exceptions.IncorrectFormatOfDataException;
import core.basesyntax.exceptions.QuantityLessThanNullException;
import core.basesyntax.exceptions.ReportIsNullException;
import core.basesyntax.exceptions.StorageIsNullException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.strategy.ActionService;
import core.basesyntax.strategy.ActionTypeService;
import java.util.List;

public class ProductCounterServiceImpl implements ProductCounterService {
    private final ActionService actionService;

    public ProductCounterServiceImpl(ActionService actionService) {
        if (actionService == null) {
            throw new ActionServiceIsNullException("ActionService can't be null");
        }
        this.actionService = actionService;
    }

    @Override
    public void countTheProducts(List<FruitTransaction> fruitTransactions, Storage storage) {
        if (fruitTransactions == null) {
            throw new ReportIsNullException("Report can't be null");
        }
        if (storage == null) {
            throw new StorageIsNullException("Storage can't be null");
        }
        if (fruitTransactions.size() <= 1) {
            return;
        }
        for (FruitTransaction fruitTransaction : fruitTransactions) {
            if (fruitTransaction == null) {
                throw new ReportIsNullException("Fruit transactions"
                        + "can't be null");
            }
            String productName = fruitTransaction.getProductName();
            int quantity = fruitTransaction.getQuantity();
            String actionCode = fruitTransaction.getActionCode();
            if (actionCode == null || productName == null) {
                throw new IncorrectFormatOfDataException("ActionCode"
                        + " and ProductName can't be null");
            }
            if (actionCode.isEmpty() || productName.isEmpty()) {
                throw new IncorrectFormatOfDataException(
                        "Fruit transaction: action/product must not be blank -> "
                                + fruitTransaction
                );
            }
            if (quantity < 0) {
                throw new QuantityLessThanNullException("Quantity must be "
                        + "greater than or equal to 0: " + quantity);
            }
            ActionTypeService action = actionService.getStrategy(actionCode);
            action.applyTheQuantity(storage, productName, quantity);
        }
    }
}
