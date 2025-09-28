package core.basesyntax.service.impl;
import core.basesyntax.service.ReportDataParserService;
import core.basesyntax.exeptions.IncorrectFormatOfDataException;
import core.basesyntax.exeptions.ReportsListEmptyException;
import core.basesyntax.exeptions.ReportsListNullException;
import java.util.List;
public class ReportDataParserServiceImpl implements ReportDataParserService {
    private static final int INDEX_OF_ACTION = 0;
    private static final int INDEX_OF_PRODUCT = 1;
    private static final int INDEX_OF_QUANTITY = 2;
    private static final int NUMBER_OF_ARRAY_COLUMN = 3;
    private static final String COLUMN_SEPARATOR= ",";

    public String[][] parseReportToArray(List<String> report) {
        if (report == null) {
            throw new ReportsListNullException("Report cannot be null");
        }
        if (report.isEmpty()) {
            throw new ReportsListEmptyException("Report cannot be empty" + report);
        }
        // 1st element - action, 2nd element - product, 3rd element - quantity
        String[][] arrayOfData = new String[report.size()][NUMBER_OF_ARRAY_COLUMN];
        for (int i = 1; i < report.size(); i++) {
            String line = report.get(i);
            String[] elementsOfLineOfReport = line.split(COLUMN_SEPARATOR, -1);
            if (elementsOfLineOfReport.length != NUMBER_OF_ARRAY_COLUMN) {
                throw new IncorrectFormatOfDataException(
                        "Line " + (i + 1) + " has " + elementsOfLineOfReport.length +
                                " column(s), expected " + NUMBER_OF_ARRAY_COLUMN + ": \""
                                + line + "\""
                );
            }
            String action  = elementsOfLineOfReport[INDEX_OF_ACTION].trim();
            String product = elementsOfLineOfReport[INDEX_OF_PRODUCT].trim();
            String quantityStr = elementsOfLineOfReport[INDEX_OF_QUANTITY].trim();
            if (action.isEmpty() || product.isEmpty() || quantityStr.isEmpty()) {
                throw new IncorrectFormatOfDataException(
                        "Line " + (i + 1) + " has empty action/product/quantity: \"" + line + "\""
                );
            }
            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                throw new IncorrectFormatOfDataException(
                        "Line " + (i + 1) + ": quantity is not a valid integer: \""
                                + quantityStr + "\"", e
                );
            }
            arrayOfData[i][INDEX_OF_ACTION] = action;
            arrayOfData[i][INDEX_OF_PRODUCT] = product;
            arrayOfData[i][INDEX_OF_QUANTITY] = String.valueOf(quantity);
        }
        return arrayOfData;
    }
}
