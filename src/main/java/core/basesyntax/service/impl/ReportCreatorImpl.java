package core.basesyntax.service.impl;

import core.basesyntax.service.ReportCreator;
import core.basesyntax.exeptions.IncorrectFormatOfDataException;
import core.basesyntax.exeptions.QuantityLessThanNullException;
import java.util.Map;

public class ReportCreatorImpl implements ReportCreator {
    private static final String NAME_OF_COLUMNS = "fruit,quantity";
    private static final String SYMBOL_OF_LINE_BREAK = System.lineSeparator();
    private static final String SYMBOL_OF_DATA_SEPARATION = ",";

    @Override
    public String getReport(Map<String, Integer> products) {
        if (products == null) {
            throw new IncorrectFormatOfDataException("Products map can't be null");
        }
        StringBuilder report = new StringBuilder();
        report.append(NAME_OF_COLUMNS).append(SYMBOL_OF_LINE_BREAK);
        for(Map.Entry<String, Integer> entry : products.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IncorrectFormatOfDataException("The product can't be null");
            }
            if (entry.getKey().isBlank()) {
                throw new IncorrectFormatOfDataException("The product name can't be blank: " + entry.getKey());
            }
            if (entry.getValue() < 0) {
                throw new QuantityLessThanNullException("The quantity can't be negative: " + entry.getValue());
            }
            report.append(entry.getKey()).append(SYMBOL_OF_DATA_SEPARATION)
                    .append(entry.getValue())
                    .append(SYMBOL_OF_LINE_BREAK);
        }
        return report.toString();
    }
}
