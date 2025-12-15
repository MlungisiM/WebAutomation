package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.ScreenshotUtil;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();
    private static final ExtentReports extent = ExtentManager.getExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }

    // Attach screenshot to current test
    public static synchronized void logScreenshot(String screenshotName) {
        var t = getTest();
        if (t == null) {
            startTest(screenshotName, screenshotName);
            t = getTest();
        }
        String path = ScreenshotUtil.takeScreenshot(screenshotName);
        if (path != null && !path.isBlank()) {
            try {
                t.addScreenCaptureFromPath(path); // keep as secondary attachment
            } catch (Exception e) {
                t.info("Failed to attach screenshot: " + e.getMessage());
            }
        } else {
            t.info("Screenshot path was null/blank; nothing attached.");

        }
    }
}