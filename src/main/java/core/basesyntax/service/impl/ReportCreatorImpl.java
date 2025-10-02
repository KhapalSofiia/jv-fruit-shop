package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportCreator;
import core.basesyntax.exceptions.IncorrectFormatOfDataException;
import core.basesyntax.exceptions.QuantityLessThanNullException;
import java.util.Map;

public class ReportCreatorImpl implements ReportCreator {
    private static final String NAME_OF_COLUMNS = "fruit,quantity";
    private static final String SYMBOL_OF_LINE_BREAK = System.lineSeparator();
    private static final String SYMBOL_OF_DATA_SEPARATION = ",";
    @Override
    public String getReport(Storage storage) {
        if (storage == null) {
            throw new IncorrectFormatOfDataException("Storage can't be null");
        }
        StringBuilder report = new StringBuilder();
        report.append(NAME_OF_COLUMNS).append(SYMBOL_OF_LINE_BREAK);
        for(Map.Entry<String, Integer> entry : storage.getStorage().entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IncorrectFormatOfDataException("The product can't be null");
            }
            if (entry.getKey().isBlank()) {
                throw new IncorrectFormatOfDataException("The product name can't be blank: "
                        + entry.getKey());
            }
            if (entry.getValue() < 0) {
                throw new QuantityLessThanNullException("The quantity can't be negative: "
                        + entry.getValue());
            }
            report.append(entry.getKey()).append(SYMBOL_OF_DATA_SEPARATION)
                    .append(entry.getValue())
                    .append(SYMBOL_OF_LINE_BREAK);
        }
        return report.toString();
    }
}
