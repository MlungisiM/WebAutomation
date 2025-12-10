
package actions;

import base.BaseClass;
import factory.DriverFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.LoginPage;

public class CregalinkLoginActions extends BaseClass {
    private LoginPage _loginPage;

    private String sso_username;
    private String sso_password;
    private String cregalink_username;
    private String cregalink_password;

    private static final String USERNAME_MISSING = "Username is missing in properties file.";
    private static final String PASSWORD_MISSING = "Password is missing in properties file.";

    /** Must be called after BaseClass.setUp() so Driver & properties exist */
    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized. Call BaseClass.setUp() first.");
        }
        // Initialize page object
        _loginPage = new LoginPage();

        // Read credentials after properties have been loaded into DriverFactory.prop
        sso_username = DriverFactory.prop.getProperty("sso_username");
        sso_password = DriverFactory.prop.getProperty("sso_password");
        cregalink_username = DriverFactory.prop.getProperty("cregalink_username");
        cregalink_password = DriverFactory.prop.getProperty("cregalink_password");
    }

    public void loginValidUsername() throws Exception {
        ensureInit();

        // Validate inputs safely (use || with null/blank checks)
        if (isBlank(sso_username) && isBlank(cregalink_username)) {
            log.error(USERNAME_MISSING);
            throw new IllegalStateException(USERNAME_MISSING);
        }
        if (isBlank(sso_password) && isBlank(cregalink_password)) {
            log.error(PASSWORD_MISSING);
            throw new IllegalStateException(PASSWORD_MISSING);
        }

        try {
            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.cregalink_username_textbox));
            _loginPage.cregalink_username_textbox.click();
            _loginPage.cregalink_username_textbox.sendKeys(cregalink_username);

            _loginPage.cregalink_password_textbox.click();
            _loginPage.cregalink_password_textbox.sendKeys(cregalink_password);

            _loginPage.cregalink_login_button.click();

            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.disclaimer_checkbox));
            _loginPage.disclaimer_checkbox.click();

            getWait().ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(_loginPage.accept_disclaimer_button))
                    .click();

            log.info("User accepted the Cregalink disclaimer");
            Assert.assertTrue(_loginPage.logout_button.isDisplayed(),
                    "Logout button not displayed after login.");
            log.info("User logged in successfully with username: {}", cregalink_username);
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    // --- helpers ---
    private void ensureInit() {
        if (_loginPage == null) {
            throw new IllegalStateException("CregalinkLoginActions.init() was not called. Call it in @BeforeMethod.");
        }
    }
    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}
