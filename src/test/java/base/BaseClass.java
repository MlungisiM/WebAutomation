package base;

import actions.CregalinkLoginActions;
import actions.DaaServiceActions;
import actions.DaaServiceLoginActions;
import actions.DaaSubmissionsActions;
import configurations.DbUtils;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DaaServicePage;
import pages.DaaSubmissionsPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.UserDefinedException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.function.Function;

public abstract class BaseClass extends DriverFactory {

    public static Logger log = LogManager.getLogger(BaseClass.class);
    private static final Properties prop = new Properties();
    public static final int AUT_MAX_WAIT = 60;

//    private LoginPage _loginPage;
//    private HomePage _homePage;
//    private DaaSubmissionsPage _daaSubmissionsPage;
//    private DaaServicePage _daaServicePage;
//
//
//    protected DaaSubmissionsActions submissionsActions;
//    protected DaaServiceActions daaServiceActions;
//    protected CregalinkLoginActions cregalinkLoginActions;
//    protected DaaServiceLoginActions daaServiceLoginActions;
//
//
//    public CregalinkLoginActions loginActions() { return cregalinkLoginActions; }
//    public DaaSubmissionsActions submissions() { return submissionsActions; }
//    public DaaServiceActions daaService() { return daaServiceActions; }
//    public DaaServiceLoginActions daaServiceLogin() { return daaServiceLoginActions; }




    @BeforeMethod
    public void setUp() throws Exception {
        // 1) Load properties once
        if (DriverFactory.prop == null) {
            Properties p = new Properties();

            // Prefer classpath loading (src/test/resources/config.properties)
            try (InputStream is = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("web_config.properties")) {
                if (is == null) {
                    // Fallback to explicit path if classpath not found
                    String cfgPath = Paths.get("src", "test", "resources", "web_config.properties").toString();
                    try (FileInputStream fis = new FileInputStream(cfgPath)) {


                        p.load(fis);
                    }
                } else {
                    p.load(is);
                }
            }

            DriverFactory.prop = p; // <-- critical
            log.info("Config loaded. db.url={}", p.getProperty("db.url"));
        }
        // 2) Initialize DB pool *once*
        DbUtils.init(DriverFactory.prop);

        // 3) (Optional) Initialize WebDriver here if your test needs UI
         DriverFactory.initDriver();

        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
//        _loginPage = new LoginPage();
//        _homePage = new HomePage();
//        _daaSubmissionsPage = new DaaSubmissionsPage();
//        _daaServicePage = new DaaServicePage();
//
//        submissionsActions = new DaaSubmissionsActions();
//        daaServiceActions = new DaaServiceActions();
//        cregalinkLoginActions = new CregalinkLoginActions();
//        daaServiceLoginActions = new DaaServiceLoginActions();
    }



    @AfterMethod
    public void tearDown() {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver != null) {
                getDriver().manage().deleteAllCookies();
                getDriver().quit();
                DriverFactory.removeDriver();
                DbUtils.shutdown();
            }
        } catch (Exception e) {
            log.warn("TearDown error (driver likely already quit): {}", e.getMessage());
        }
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(AUT_MAX_WAIT));
    }

    public static Properties getProperties() {
        return prop;
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


    // Replace with explicit waits
    public void waitForElement(By locator, int timeoutInSeconds) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(AUT_MAX_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    //generate the current date and time
    public static String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        return dateFormat.format(dateNow);
    }

    public static String generateRandomEmail() {

        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generates a random number up to 9999
        return "testuser_" + generateDateTimeString() + "_" + randomNumber + "@cgic.co.za";
    }

    //generates a random phone number which starts with 27 (so it becomes a valid South African phone number)
    public CharSequence SA_random_phone_number() {

        Random rand = new Random();
        int num1 = 27;
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros
        return df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
    }

    public static void navigateTo(String data) throws Exception {
        try {
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().navigate().to(data);
        } catch (Exception e) {
            throw new UserDefinedException("<<< Unable to Navigate to " + data + " >>> " + e.getMessage());
        }
    }

    public static void WaitForPageLoad(int MaxWaitTime) throws Exception{

        if(MaxWaitTime<=0){
            MaxWaitTime = AUT_MAX_WAIT;
        }
        Thread.sleep(3000);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(MaxWaitTime))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        boolean JqueryExecuted = wait.until(new Function<WebDriver, Boolean>(){
            public Boolean apply(WebDriver d){
                return ((JavascriptExecutor)d).executeScript("return jQuery.active").toString().equals("0");
            }
        });

        boolean JavaScriptExecuted = wait.until(new Function<WebDriver, Boolean>(){
            public Boolean apply(WebDriver d){
                return ((JavascriptExecutor)d).executeScript("return document.readyState").toString().equals("complete");
            }
        });

        if(!JqueryExecuted || !JavaScriptExecuted){
            throw new UserDefinedException("WebPage is taking time to process. Max Wait time for element display " + MaxWaitTime);
        }
    }
}
