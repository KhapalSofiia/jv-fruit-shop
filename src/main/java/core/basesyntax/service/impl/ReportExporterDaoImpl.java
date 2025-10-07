package core.basesyntax.service.impl;

import core.basesyntax.exceptions.DataIsNullException;
import core.basesyntax.exceptions.InvalidDataException;
import core.basesyntax.exceptions.WorkWithFileException;
import core.basesyntax.service.ReportExporterDao;
import java.io.FileWriter;
import java.io.IOException;

public class ReportExporterDaoImpl implements ReportExporterDao {
    private final String fileName;

    public ReportExporterDaoImpl(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new InvalidDataException("File name is incorrect " + fileName);
        }
        this.fileName = fileName;
    }

    @Override
    public void writeReport(String report) {
        if (report == null) {
            throw new DataIsNullException("Report can't be null.");
        }
        if (report.isBlank()) {
            throw new InvalidDataException("Report is incorrect " + fileName);
        }
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new WorkWithFileException("Error writing report to " + fileName, e);
        }
    }
}
