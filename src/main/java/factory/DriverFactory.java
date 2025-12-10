
package factory;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    // Removed: public static Actions actions; (unsafe with ThreadLocal)
    public static Properties prop;

    // No changes needed to these standard methods
    public static WebDriver getDriver() {
        // This now returns the SelfHealingDriver instance stored in ThreadLocal
        WebDriver d = driver.get();
        return d;
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void removeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public static WebDriver initDriver() throws Exception {
        if (getDriver() == null) {
            launchBrowser(); // launches & sets driver
        }
        return getDriver(); // ✅ always return a usable driver
    }

    public static void launchBrowser() throws Exception {
        String browserName = prop.getProperty("browser").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        // Set Hudson CSP properties
        System.clearProperty("hudson.model.DirectoryBrowserSupport.CSP");
        System.setProperty(
                "hudson.model.DirectoryBrowserSupport.CSP",
                "sandbox allow-scripts; default-src 'self'; script-src * 'unsafe-eval'; img-src *; style-src * 'unsafe-inline'; font-src *"
        );

        WebDriver delegateDriver = null; // Temporary variable to hold the base WebDriver

        try {
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    if (isHeadless) chromeOptions.addArguments("--headless=new");
                    delegateDriver = new ChromeDriver(chromeOptions); // Assign to delegate
                    log.info("Chrome driver instantiated");
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) firefoxOptions.addArguments("--headless");
                    delegateDriver = new FirefoxDriver(firefoxOptions); // Assign to delegate
                    log.info("Firefox driver instantiated");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) edgeOptions.addArguments("--headless=new");
                    delegateDriver = new EdgeDriver(edgeOptions); // Assign to delegate
                    log.info("Edge driver instantiated");
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browserName);
            }

            // --- HEALENIUM INTEGRATION START ---
            // Wrap the base driver instance in the SelfHealingDriver
            WebDriver selfHealingDriver = com.epam.healenium.SelfHealingDriver.create(delegateDriver);
            driver.set(selfHealingDriver); // Set the wrapped driver into ThreadLocal
            // --- HEALENIUM INTEGRATION END ---

            long startTime = System.currentTimeMillis();
            getDriver().get(prop.getProperty("internal_dev_environment.url"));
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            long totalTime = (System.currentTimeMillis() - startTime) / 1000;
            log.info("Total time to load URL: {} seconds", totalTime);
        } catch (Exception e) {
            log.error("Unable to start {} browser", browserName, e);
            // Ensure any partially created driver is quit if an error occurs
            if (delegateDriver != null) {
                delegateDriver.quit();
            }
            throw e;
        }
    }
}
