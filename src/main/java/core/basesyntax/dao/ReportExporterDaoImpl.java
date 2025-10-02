package core.basesyntax.dao;

import core.basesyntax.exceptions.FileWriteException;
import core.basesyntax.exceptions.IncorrectFileNameException;
import core.basesyntax.exceptions.IncorrectFormatOfReportException;
import core.basesyntax.exceptions.ReportIsNullException;
import java.io.FileWriter;
import java.io.IOException;

public class ReportExporterDaoImpl implements ReportExporterDao {
    private final String fileName;

    public ReportExporterDaoImpl(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IncorrectFileNameException("File name is incorrect " + fileName);
        }
        this.fileName = fileName;
    }

    @Override
    public void writeTheReport(String report) {
        if (report == null) {
            throw new ReportIsNullException("Report can't be null.");
        }
        if (report.isBlank()) {
            throw new IncorrectFormatOfReportException("Report is incorrect " + fileName);
        }
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new FileWriteException("Error writing report to " + fileName, e);
        }
    }
}
