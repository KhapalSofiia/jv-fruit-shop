package core.basesyntax.service.impl;

import java.util.ArrayList;
import java.util.List;
import core.basesyntax.exceptions.IncorrectFormatOfDataException;
import core.basesyntax.exceptions.ReportsListEmptyException;
import core.basesyntax.exceptions.ReportsListNullException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ReportDataParserService;

public class ReportDataParserServiceImpl implements ReportDataParserService {
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_PRODUCT = 1;
    private static final int INDEX_OF_QUANTITY = 2;
    private static final String COLUMN_SEPARATOR = ",";

    public List<FruitTransaction> parseReportToList(List<String> report) {
        if (report == null) {
            throw new ReportsListNullException("Report cannot be null");
        }
        if (report.isEmpty()) {
            throw new ReportsListEmptyException("Report cannot be empty" + report);
        }
        List<FruitTransaction> fruitTransactions = new ArrayList<>();
        for (int i = 1; i < report.size(); i++) {
            String line = report.get(i);
            String[] elementsOfLine = line.split(COLUMN_SEPARATOR);
            String actionCode = trimOrEmpty(elementsOfLine[INDEX_OF_ACTION]);
            int quantity = getQuantity(elementsOfLine[INDEX_OF_QUANTITY], i);
            String productName = trimOrEmpty(elementsOfLine[INDEX_OF_PRODUCT]);
            if (!actionCode.matches("^[bspr]$")) {
                throw new IncorrectFormatOfDataException(
                        "Line " + (i + 1) + ": invalid action code '"
                                + actionCode + "'. Expected one of b/s/p/r"
                );
            }
            if (productName.isEmpty()) {
                throw new IncorrectFormatOfDataException(
                        "Line " + (i + 1) + ": product name must not be blank -> " + line
                );
            }
            fruitTransactions.add(new FruitTransaction(actionCode, quantity, productName));
        }
        return fruitTransactions;
    }

    private static int getQuantity(String quantityString, int i) {
        int quantity;
        try {
            quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                throw new IncorrectFormatOfDataException("Line " + (i + 1)
                        + ": negative quantity: " + quantity);
            }
        } catch (NumberFormatException e) {
            throw new IncorrectFormatOfDataException(
                    "Invalid quantity at line " + (i + 1) + ": '"
                            + quantityString + "' is not a valid integer", e
            );
        }
        return quantity;
    }

    private String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }
}
