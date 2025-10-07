package core.basesyntax.strategy;

import core.basesyntax.exceptions.DataIsNullException;
import core.basesyntax.exceptions.InvalidDataException;
import java.util.Map;

public class ActionService {
    private final Map<Operation, ActionTypeService> strategies;

    public ActionService(Map<Operation, ActionTypeService> strategies) {
        if (strategies == null) {
            throw new DataIsNullException("Map of strategies is null");
        }
        this.strategies = Map.copyOf(strategies);
    }

    public ActionTypeService getStrategy(String actionCode) {
        if (actionCode == null) {
            throw new DataIsNullException("ActionCode is null");
        }
        Operation operation;
        try {
            operation = Operation.fromCode(actionCode);
        } catch (RuntimeException e) {
            throw new InvalidDataException("Unknown operation code: " + actionCode, e);
        }
        ActionTypeService strategy = strategies.get(operation);
        if (strategy == null) {
            throw new DataIsNullException("No strategy found for operation: " + operation);
        }
        return strategy;
    }
}
