package core.basesyntax.service.impl;
import core.basesyntax.db.Storage;
import core.basesyntax.exeptions.*;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.strategy.ActionService;
import core.basesyntax.strategy.ActionTypeService;
import java.util.Arrays;
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
    public void countTheProducts(String[][] report, Storage storage) {
        if (report == null) {
            throw new ReportIsNullException("Report can't be null");
        }
        if (storage == null) {
            throw new StorageIsNullException("Storage can't be null");
        }
        if (report.length <= 1) {
            return;
        }
        for (int i = 1; i < report.length; i++) {
            String[] row = report[i];
            int line = i + 1;
            if (row == null || row.length != NUMBER_OF_COLUMNS) {
                throw new IncorrectFormatOfDataException(
                        "Line " + line + ": expected " + NUMBER_OF_COLUMNS +
                                " columns, but was " + (row == null ? "null" : row.length)
                );
            }
            String actionCode = trimOrEmpty(row[INDEX_OF_ACTION]);
            String product    = trimOrEmpty(row[INDEX_OF_PRODUCT_NAME]);
            String qtyRaw     = trimOrEmpty(row[INDEX_OF_QUANTITY]);
            if (actionCode.isEmpty() || product.isEmpty() || qtyRaw.isEmpty()) {
                throw new IncorrectFormatOfDataException(
                        "Line " + line + ": action/product/quantity must not be blank -> " +
                                Arrays.toString(row)
                );
            }
            int quantity = getQuantity(report, i);
            ActionTypeService action = actionService.getStrategy(report[i][INDEX_OF_ACTION]);
            action.applyTheQuantity(storage, report[i][INDEX_OF_PRODUCT_NAME], quantity);
        }
    }

    private String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private static int getQuantity(String[][] report, int i) {
        int quantity;
        try {
            quantity = Integer.parseInt(report[i][INDEX_OF_QUANTITY]);
        } catch (NumberFormatException e) {
            throw new IncorrectFormatOfDataException(
                    "Invalid quantity at line " + (i + 1) + ": '" + report[i][INDEX_OF_QUANTITY]
                            + "' is not a valid integer", e
            );
        }
        if (quantity < 0) {
            throw new QuantityLessThanNullException(
                    "Negative quantity at line " + (i + 1) + ": " + quantity
            );
        }
        return quantity;
    }
}
