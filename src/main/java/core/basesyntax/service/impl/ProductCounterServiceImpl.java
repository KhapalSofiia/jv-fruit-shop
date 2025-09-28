package core.basesyntax.service.impl;


import core.basesyntax.service.ProductCounterService;
import core.basesyntax.strategy.ActionService;
import core.basesyntax.strategy.ActionTypeService;
import core.basesyntax.exeptions.ActionServiceIsNullException;
import core.basesyntax.exeptions.IncorrectFormatOfDataException;
import core.basesyntax.exeptions.QuantityLessThanNullException;
import core.basesyntax.exeptions.ReportIsNullException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProductCounterServiceImpl implements ProductCounterService {
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_PRODUCT_NAME = 1;
    private static final int INDEX_OF_QUANTITY = 2;
    private static final int NUMBER_OF_COLUMNS = 3;
    private final ActionService actionService;

    public ProductCounterServiceImpl(ActionService actionService) {
        if (actionService == null) {
            throw new ActionServiceIsNullException("ActionService can't be null");
        }
        this.actionService = actionService;
    }

    @Override
    public Map<String, Integer> countTheProducts(String[][] report) {
        if (report == null) {
            throw new ReportIsNullException("Report can't be null");
        }
        if (report.length <= 1) {
            return new HashMap<>();
        }
        Map<String, Integer> quantityOfEveryProduct = new HashMap<>();
        for (int i = 1; i < report.length; i++) {
            if (report[i].length != NUMBER_OF_COLUMNS) {
                throw new IncorrectFormatOfDataException("Expected number of column is "
                        + NUMBER_OF_COLUMNS + " but was " + report.length);
            }
            if (report[i][INDEX_OF_QUANTITY] == null
                    || report[i][INDEX_OF_ACTION] == null
                    || report[i][INDEX_OF_PRODUCT_NAME] == null) {
                throw new IncorrectFormatOfDataException("The action, product name or quantity can't null.");
            }
            if (report[i][INDEX_OF_PRODUCT_NAME].isBlank()
                    || report[i][INDEX_OF_QUANTITY].isBlank()
                    || report[i][INDEX_OF_ACTION].isBlank()) {
                throw new IncorrectFormatOfDataException("The action, product name or quantity can't empty "
                        + Arrays.toString(report[i]));
            }
            int currentQuantity = 0;
            if (quantityOfEveryProduct.containsKey(report[i][INDEX_OF_PRODUCT_NAME])) {
                currentQuantity = quantityOfEveryProduct.get(report[i][INDEX_OF_PRODUCT_NAME]);;
            }
            ActionTypeService action = actionService.getStrategy(report[i][INDEX_OF_ACTION]);
            int newQuantity = action.getTheAction(currentQuantity,
                    Integer.parseInt(report[i][INDEX_OF_QUANTITY]));
            if (newQuantity < 0) {
                throw new QuantityLessThanNullException("The quantity can't be negative: "
                        + newQuantity);
            }
            quantityOfEveryProduct.put(report[i][INDEX_OF_PRODUCT_NAME], newQuantity);
        }
        return quantityOfEveryProduct;
    }
}
