package core.basesyntax.strategy;

import core.basesyntax.exceptions.StrategyIsNullException;
import core.basesyntax.exceptions.StrategyMapIsNullException;
import core.basesyntax.exceptions.UnknownOperationException;
import java.util.Map;

public class ActionService {
    private final Map<Operation, ActionTypeService> strategies;

    public ActionService(Map<Operation, ActionTypeService> strategies) {
        if (strategies == null) {
            throw new StrategyMapIsNullException("Map of strategies is null");
        }
        this.strategies = Map.copyOf(strategies);
    }

    public ActionTypeService getStrategy(String actionCode) {
        if (actionCode == null) {
            throw new StrategyIsNullException("ActionCode is null");
        }
        Operation operation;
        try {
            operation = Operation.fromCode(actionCode);
        } catch (RuntimeException e) {
            throw new UnknownOperationException("Unknown operation code: " + actionCode, e);
        }
        ActionTypeService strategy = strategies.get(operation);
        if (strategy == null) {
            throw new StrategyIsNullException("No strategy found for operation: " + operation);
        }
        return strategy;
    }
}
