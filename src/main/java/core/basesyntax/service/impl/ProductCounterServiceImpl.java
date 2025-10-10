package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.InvalidDataException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.strategy.ActionService;
import core.basesyntax.strategy.ActionTypeService;
import java.util.List;

public class ProductCounterServiceImpl implements ProductCounterService {
    private final ActionService actionService;

    public ProductCounterServiceImpl(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public void countTheProducts(List<FruitTransaction> fruitTransactions, Storage storage) {
        for (FruitTransaction fruitTransaction : fruitTransactions) {
            String productName = fruitTransaction.getProductName();
            int quantity = fruitTransaction.getQuantity();
            String actionCode = fruitTransaction.getActionCode();
            if (quantity < 0) {
                throw new InvalidDataException("Quantity must be "
                        + "greater than or equal to 0: " + quantity);
            }
            ActionTypeService action = actionService.getStrategy(actionCode);
            action.applyTheQuantity(storage, productName, quantity);
        }
    }
}
