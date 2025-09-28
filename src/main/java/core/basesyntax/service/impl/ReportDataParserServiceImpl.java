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
        for (int i = 0; i < report.size(); i++) {
            if (report.get(i) == null || report.get(i).isBlank()) {
                throw new IncorrectFormatOfDataException("The line " + i
                        + " has incorrect format" + report.get(i));
            }
            String[] elementsOfLineOfReport = report.get(i).split(COLUMN_SEPARATOR);
            if (elementsOfLineOfReport.length != NUMBER_OF_ARRAY_COLUMN) {
                throw new IncorrectFormatOfDataException("Line " + i + " has "
                        + elementsOfLineOfReport.length + " column(s), expected "
                        + NUMBER_OF_ARRAY_COLUMN + ": " + report.get(i));
            }
            arrayOfData[i][INDEX_OF_ACTION] = elementsOfLineOfReport[INDEX_OF_ACTION];
            arrayOfData[i][INDEX_OF_PRODUCT] = elementsOfLineOfReport[INDEX_OF_PRODUCT];
            arrayOfData[i][INDEX_OF_QUANTITY] = elementsOfLineOfReport[INDEX_OF_QUANTITY];
        }
        return arrayOfData;
    }
}
