package core.basesyntax;
import core.basesyntax.dao.ReportExporterDao;
import core.basesyntax.dao.ReportExporterDaoImpl;
import core.basesyntax.dao.ReportExtractorDao;
import core.basesyntax.dao.ReportExtractorDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.service.ProductCounterService;
import core.basesyntax.service.ReportCreator;
import core.basesyntax.service.ReportDataParserService;
import core.basesyntax.service.impl.ProductCounterServiceImpl;
import core.basesyntax.service.impl.ReportCreatorImpl;
import core.basesyntax.service.impl.ReportDataParserServiceImpl;
import java.io.File;
import java.util.List;
import static core.basesyntax.strategy.ActionConfig.createResolver;

/**
 * Feel free to remove this class and create your own.
 */
public class Main {
    // HINT: In the `public static void main(String[] args)` it is better to create instances of your classes,
    // and call their methods, but do not write any business logic in the `main` method!
    public static void main(String[] args) {
        //Зчитування файлу
        Storage storage = new Storage();
        String resourceName = "report.csv";
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        ReportExtractorDao fileReader = new ReportExtractorDaoImpl(file.getPath());
        List<String> inputReport = fileReader.getReport();
        //Перетворення репорта у масив
        ReportDataParserService reportDataParserService = new ReportDataParserServiceImpl();
        String[][] parsedReport = reportDataParserService.parseReportToArray(inputReport);
        //Підрахунок продуктів
        ProductCounterService productCounterService = new ProductCounterServiceImpl(createResolver());
        productCounterService.countTheProducts(parsedReport, storage);
        //Створення та запис репорту
        ReportExporterDao reportExporterDao =
                new ReportExporterDaoImpl("src/main/resources/newReport.csv");
        ReportCreator report = new ReportCreatorImpl();
        String reportWrite = report.getReport(storage);
        reportExporterDao.writeTheReport(reportWrite);
    }
}
