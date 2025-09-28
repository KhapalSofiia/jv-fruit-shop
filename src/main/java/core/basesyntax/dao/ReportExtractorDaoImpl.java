package core.basesyntax.dao;
import core.basesyntax.exeptions.FileDoesnExistsException;
import core.basesyntax.exeptions.FileReadException;
import core.basesyntax.exeptions.IncorrectFileNameException;
import core.basesyntax.exeptions.ReadNotPossibleException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ReportExtractorDaoImpl implements ReportExtractorDao {
    private final String fileName;

    public ReportExtractorDaoImpl(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IncorrectFileNameException("File name is incorrect " + fileName);
        }
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileDoesnExistsException("File does not exist: " + fileName);
        }
        if (!file.canRead()) {
            throw new ReadNotPossibleException("File is not readable: " + fileName);
        }
        this.fileName = fileName;
    }

    @Override
    public List<String> getReport() {
        List<String> linesOfReport = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String lineOfReport;
            while ((lineOfReport = br.readLine()) != null) {
                linesOfReport.add(lineOfReport);
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading report from " + fileName, e);
        }
        return linesOfReport;
    }
}
