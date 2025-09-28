package core.basesyntax.strategy;

import core.basesyntax.exeptions.StrategyIsNullException;
import core.basesyntax.exeptions.StrategyMapIsNullException;
import java.util.Map;

public class ActionService {
    private final Map<String, ActionTypeService> strategies;

    public ActionService(Map<String, ActionTypeService> strategies) {
        if (strategies == null) {
            throw new StrategyMapIsNullException("Map of strategies is null");
        }
        this.strategies = Map.copyOf(strategies);
    }

    public ActionTypeService getStrategy(String actionType) {
        ActionTypeService strategy = strategies.get(actionType);
        if (strategy == null) {
            throw new StrategyIsNullException("Strategy " + actionType + " is null");
        }
        return strategy;
    }
}
