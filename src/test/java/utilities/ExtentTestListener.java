package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ExtentTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
//        log.info("Test Suite finished: " + context.getName());
//        // flush the report once all tests are done
//        ExtentManager.getExtentReports().flush();
        utilities.ExtentManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription() != null ?
                result.getMethod().getDescription() : testName;

        ExtentTestManager.startTest(testName, description);
        log.info("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        log.info("Test passed: " + result.getMethod().getMethodName());
//
//        // Capture screenshot on pass
//        String screenshotName = result.getMethod().getMethodName() + "_pass";
//        ExtentTestManager.logScreenshot(screenshotName);
//
//        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");

        String name = result.getMethod().getMethodName() + "_pass";
        attachScreenshotAndLog(name, Status.PASS, null);
        utilities.ExtentTestManager.getTest().log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
//        log.error("Test failed: " + result.getMethod().getMethodName());
//        log.error("Reason: " + result.getThrowable());
//
//        // Capture screenshot on fail
//        String screenshotName = result.getMethod().getMethodName() + "_fail";
//        ExtentTestManager.logScreenshot(screenshotName);
//
//        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

        String name = result.getMethod().getMethodName() + "_fail";
        attachScreenshotAndLog(name, Status.FAIL, result.getThrowable());
        utilities.ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());


    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        log.warn("Test skipped: " + result.getMethod().getMethodName());
//
//        // Optional: capture screenshot on skip
//        String screenshotName = result.getMethod().getMethodName() + "_skipped";
//        ExtentTestManager.logScreenshot(screenshotName);
//
//        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");

        String name = result.getMethod().getMethodName() + "_skipped";
        attachScreenshotAndLog(name, Status.SKIP, result.getThrowable());
        utilities.ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // usually not used
    }


    private void attachScreenshotAndLog(String screenshotName, Status status, Throwable throwable) {
        try {
            // Uses your ScreenshotUtil to create the file under target/reports/screenshots/
            String path = utilities.ScreenshotUtil.takeScreenshot(screenshotName);
            if (path != null && !path.isBlank()) {
                // Inline image attachment
                utilities.ExtentTestManager.getTest().log(
                        status,
                        String.valueOf((throwable != null ? throwable : "Screenshot: " + screenshotName)),
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build()
                );
            } else {
                utilities.ExtentTestManager.getTest().log(status, "No screenshot captured (path null/blank)");
            }
        } catch (Exception e) {
            utilities.ExtentTestManager.getTest().log(status, "Screenshot capture failed: " + e.getMessage());
        }
    }

}
