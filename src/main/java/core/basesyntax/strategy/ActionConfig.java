package core.basesyntax.strategy;
import java.util.HashMap;
import java.util.Map;
public class ActionConfig {

    public static ActionService  createResolver() {
        Map<Operation, ActionTypeService> strategies = new HashMap<>();
        strategies.put(Operation.BALANCE, new BalanceActionTypeServiceImpl());
        strategies.put(Operation.SUPPLY, new SupplyActionTypeServiceImpl());
        strategies.put(Operation.PURCHASE, new PurchaseActionTypeServiceImpl());
        strategies.put(Operation.RETURN, new ReturnActionTypeServiceImpl());
        return new ActionService(strategies);
    }
}
