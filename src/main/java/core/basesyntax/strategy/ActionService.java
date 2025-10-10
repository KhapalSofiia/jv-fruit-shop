package core.basesyntax.strategy;

import java.util.Map;

public class ActionService {
    private final Map<Operation, ActionTypeService> strategies;

    public ActionService(Map<Operation, ActionTypeService> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public ActionTypeService getStrategy(String actionCode) {
        Operation operation = Operation.fromCode(actionCode);
        return strategies.get(operation);
    }
}
