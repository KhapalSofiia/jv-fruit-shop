package core.basesyntax.strategy;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {

    public static ActionService  createResolver() {
        Map<String, ActionTypeService> strategies = new HashMap<>();
        strategies.put("b", new BalanceActionTypeServiceImpl());
        strategies.put("s", new SupplyActionTypeServiceImpl());
        strategies.put("p", new PurchaseActionTypeServiceImpl());
        strategies.put("r", new ReturnActionTypeServiceImpl());
        return new ActionService(strategies);
    }
}
