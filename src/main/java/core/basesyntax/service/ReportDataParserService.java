package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;

import java.util.List;
public interface ReportDataParserService {
    public List<FruitTransaction> parseReportToList(List<String> report);
}
