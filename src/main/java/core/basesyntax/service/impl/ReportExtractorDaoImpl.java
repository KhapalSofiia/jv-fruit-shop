package core.basesyntax.service.impl;

import core.basesyntax.exceptions.FileReadException;
import core.basesyntax.service.ReportExtractorDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReportExtractorDaoImpl implements ReportExtractorDao {

    @Override
    public List<String> getReport(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input steam can't be null.");
        }
        List<String> linesOfReport = new ArrayList<>();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, java.nio.charset.StandardCharsets.UTF_8));
        try {
            String lineOfReport;
            while ((lineOfReport = br.readLine()) != null) {
                linesOfReport.add(lineOfReport);
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading report from stream", e);
        }
        return linesOfReport;
    }
}
