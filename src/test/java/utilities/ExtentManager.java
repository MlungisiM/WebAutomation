package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;

public class ExtentManager {

    private static ExtentReports extent;

    // Returns singleton ExtentReports instance
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // Save report inside target/reports/
            String reportPath = Paths.get("target", "reports", "cregalink-automation-results.html").toString();

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("CregaLink Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
