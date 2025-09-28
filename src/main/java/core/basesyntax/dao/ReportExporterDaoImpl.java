package core.basesyntax.dao;

import core.basesyntax.exeptions.FileWriteException;
import core.basesyntax.exeptions.IncorrectFileNameException;
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
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(report);
        } catch (IOException e) {
            throw new FileWriteException("Error writing report to " + fileName
                    + ": " + e.getMessage());
        }
    }
}
