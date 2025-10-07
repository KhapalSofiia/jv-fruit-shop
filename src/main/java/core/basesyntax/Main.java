package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.exceptions.DataIsNullException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.service.ReportCreator;
import core.basesyntax.service.ReportDataParserService;
import core.basesyntax.service.ReportExporterDao;
import core.basesyntax.service.ReportExtractorDao;
import core.basesyntax.service.impl.ProductCounterServiceImpl;
import core.basesyntax.service.impl.ReportCreatorImpl;
import core.basesyntax.service.impl.ReportDataParserServiceImpl;
import core.basesyntax.service.impl.ReportExporterDaoImpl;
import core.basesyntax.service.impl.ReportExtractorDaoImpl;
import core.basesyntax.strategy.ActionConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Feel free to remove this class and create your own.
 */
public class Main {
    private static final String DATA_FILE = "report.csv";
    private static final String REPORT_FILE = "src/main/resources/finalReport.csv";
    // HINT: In the `public static void main(String[] args)`
    // it is better to create instances of your classes,
    // and call their methods, but do not write any business logic in the `main` method!

    public static void main(String[] args) {
        // Зчитування файлу
        Storage storage = new Storage();
        ClassLoader classLoader = Main.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(DATA_FILE)) {
            if (inputStream == null) {
                throw new DataIsNullException("Resource '" + DATA_FILE
                        + "' not found on the classpath");
            }
            ReportExtractorDao fileReader = new ReportExtractorDaoImpl();
            List<String> inputReport = fileReader.getReport(inputStream);
            // Перетворення репорта у масив
            ReportDataParserService reportDataParserService =
                    new ReportDataParserServiceImpl();
            List<FruitTransaction> parsedReport =
                    reportDataParserService.parseReportToList(inputReport);
            // Підрахунок продуктів
            ActionConfig actionConfig = new ActionConfig();
            ProductCounterService productCounterService =
                    new ProductCounterServiceImpl(actionConfig.createResolver());
            productCounterService.countTheProducts(parsedReport, storage);
            // Створення та запис репорту
            ReportExporterDao reportExporterDao =
                    new ReportExporterDaoImpl(REPORT_FILE);
            ReportCreator report = new ReportCreatorImpl();
            String reportWrite = report.getReport(storage);
            reportExporterDao.writeReport(reportWrite);
        } catch (IOException e) {
            throw new DataIsNullException("Impossible to read data from "
                    + DATA_FILE, e);
        }
    }
}
