package core.basesyntax.service;

import java.util.List;

public interface ReportDataParserService {
    public String[][] parseReportToArray(List<String> report);
}
