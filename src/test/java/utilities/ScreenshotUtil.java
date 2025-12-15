
package utilities;

import factory.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.WrapsDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR =
            System.getProperty("user.dir") + File.separator + "target" + File.separator + "reports"
                    + File.separator + "screenshots" + File.separator;

    /**
     * Takes a screenshot and stores it under target/reports/screenshots.
     * Returns a relative path suitable for Extent: screenshots/<file>.png
     */
    public static String takeScreenshot(String screenshotName) {

        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            log.error("Driver is null; cannot take screenshot.");
            return null;
        }

        try {
            // 1) Unwrap common wrappers/proxies (WrapsDriver + reflection fallback)
            WebDriver usableDriver = unwrapDriver(driver);

            // 2) Ensure we have a TakesScreenshot-capable instance (augment remote if required)
            TakesScreenshot ts = getScreenshotCapableDriver(usableDriver);
            if (ts == null) {
                log.error("Unable to obtain a TakesScreenshot instance from driver type: {}", usableDriver.getClass().getName());
                return null;
            }

            // 3) Take screenshot (can throw WebDriverException if session is gone/closed)
            File source = ts.getScreenshotAs(OutputType.FILE);

            // 4) Ensure screenshot directory exists
            Path dirPath = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(dirPath);

            // 5) Build file name (safe/sanitized)
            String safeName = sanitizeFileName(screenshotName);
            String stamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String fileName = safeName + "_" + stamp + ".png";
            String fullPath = SCREENSHOT_DIR + fileName;

            // 6) Copy file
            File destination = new File(fullPath);
            FileUtils.copyFile(source, destination);

            log.info("Screenshot saved at: {}", fullPath);

            // Return relative path for Extent report
            return "screenshots/" + fileName;

        } catch (NoSuchSessionException e) {
            log.error("Cannot take screenshot: WebDriver session is already closed. {}", e.getMessage(), e);
            return null;
        } catch (UnhandledAlertException e) {
            log.error("Cannot take screenshot due to unexpected alert. {}", e.getMessage(), e);
            return null;
        } catch (WebDriverException e) {
            // Most common failure when driver quits early or wrapper can't capture
            log.error("WebDriverException while taking screenshot: {}", e.getMessage(), e);
            return null;
        } catch (IOException e) {
            log.error("IO error while saving screenshot: {}", e.getMessage(), e);
            return null;
        } catch (Exception e) {
            // Catch-all to ensure we always log the true root cause
            log.error("Unexpected error while taking screenshot: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Attempts to unwrap wrapper/proxy drivers.
     * - Handles WrapsDriver wrappers in a loop
     * - Adds reflection fallback for wrappers that do NOT implement WrapsDriver (common in some libs)
     */
    private static WebDriver unwrapDriver(WebDriver driver) {
        WebDriver current = driver;

        // A) Standard Selenium wrapper unwrapping
        int guard = 0;
        while (current instanceof WrapsDriver && guard++ < 10) {
            WebDriver wrapped = ((WrapsDriver) current).getWrappedDriver();
            if (wrapped == null || wrapped == current) break;
            current = wrapped;
        }

        // B) Reflection fallback for non-WrapsDriver wrappers (e.g., some self-healing/decorators)
        // Try a few common method names used by wrappers
        current = reflectionUnwrap(current);

        return current;
    }

    private static WebDriver reflectionUnwrap(WebDriver driver) {
        WebDriver current = driver;

        String[] candidateMethods = new String[] {
                "getWrappedDriver",
                "getDriver",
                "getDelegate",
                "getWebDriver",
                "getOriginalDriver"
        };

        for (int depth = 0; depth < 5; depth++) {
            WebDriver next = null;
            for (String methodName : candidateMethods) {
                next = tryInvokeWebDriverGetter(current, methodName);
                if (next != null && next != current) break;
            }
            if (next == null || next == current) break;
            current = next;
        }

        return current;
    }

    private static WebDriver tryInvokeWebDriverGetter(Object obj, String methodName) {
        try {
            Method m = obj.getClass().getMethod(methodName);
            Object result = m.invoke(obj);
            if (result instanceof WebDriver) {
                return (WebDriver) result;
            }
        } catch (Exception ignored) {
            // Intentionally ignore; we'll try other methods
        }
        return null;
    }

    /**
     * Returns a TakesScreenshot instance if possible.
     * If remote/grid driver isn't directly TakesScreenshot, tries Augmenter.
     */
    private static TakesScreenshot getScreenshotCapableDriver(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            return (TakesScreenshot) driver;
        }

        // Try augmenting for remote/grid drivers (Selenium)
        try {
            WebDriver augmented = new Augmenter().augment(driver);
            if (augmented instanceof TakesScreenshot) {
                return (TakesScreenshot) augmented;
            }
        } catch (Exception e) {
            log.warn("Augmenter failed to create screenshot-capable driver: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Remove characters that can break file paths on Windows/Linux and in CI.
     */
    private static String sanitizeFileName(String name) {
        if (name == null || name.trim().isEmpty()) return "screenshot";
        // Replace whitespace and illegal filename chars: \ / : * ? " < > |
        return name.trim()
                .replaceAll("\\s+", "_")
                .replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}
