package actions;

import base.BaseClass;
import factory.DriverFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;


public class CregalinkLoginActions extends BaseClass {

    private LoginPage _loginPage;
    String sso_username = DriverFactory.prop.getProperty("sso_username");
    String sso_password = DriverFactory.prop.getProperty("sso_password");

    String cregalink_username = DriverFactory.prop.getProperty("cregalink_username");
    String cregalink_password = DriverFactory.prop.getProperty("cregalink_password");

    String username_missing_message = "Username is missing in properties file.";
    String password_missing_message = "Password is missing in properties file.";


    @BeforeMethod
    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        _loginPage = new LoginPage();
    }


    public void loginValidUsername() throws Exception {

        if (sso_username == null && sso_username.isEmpty() && cregalink_username == null && cregalink_username.isEmpty()) {
            log.error("Username is missing in properties file.: {}");
            throw new IllegalStateException("Missing required property: username");
        } else if (sso_password == null && sso_password.isEmpty() && cregalink_password == null && cregalink_password.isEmpty()) {
            log.error(password_missing_message);
            throw new IllegalStateException("Missing required property: {}");
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
            Assert.assertTrue(_loginPage.logout_button.isDisplayed());
            log.info("User logged in successfully with username: {}", cregalink_username);
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void IncorrectUsername() throws Exception {

        if (sso_username == null && sso_username.isEmpty() && cregalink_username == null && cregalink_username.isEmpty()) {
            log.error(username_missing_message);
            throw new IllegalStateException("Missing required property: username");
        } else if (sso_password == null && sso_password.isEmpty() && cregalink_password == null && cregalink_password.isEmpty()) {
            log.error(password_missing_message);
            throw new IllegalStateException("Missing required property: {}");
        }

        try {
            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.cregalink_username_textbox));
            _loginPage.cregalink_username_textbox.click();
            _loginPage.cregalink_username_textbox.sendKeys("qwerty");
            _loginPage.cregalink_password_textbox.click();
            _loginPage.cregalink_password_textbox.sendKeys(cregalink_password);
            _loginPage.cregalink_login_button.click();
            String Invalidmessage = _loginPage.invalid_useridOrpassword_message.getText();
            getWait().until(ExpectedConditions.visibilityOf(_loginPage.invalid_useridOrpassword_message));
            Assert.assertTrue(Invalidmessage.contains("Invalid user or password"));
            log.info("Validation Successful: User could not login using an incorrect username when using username: {}");
        } catch (Exception e) {
            log.error("Validation failed, user could login using an incorrect username: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void loginEmptyUsername() throws Exception {

        if (sso_password == null && sso_password.isEmpty() && cregalink_password == null && cregalink_password.isEmpty()) {
            log.error(password_missing_message);
            throw new IllegalStateException("Missing required property: {}");
        }

        try {
            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.cregalink_username_textbox));
            _loginPage.cregalink_password_textbox.click();
            _loginPage.cregalink_password_textbox.sendKeys(cregalink_password);
            _loginPage.cregalink_login_button.click();
            String enteruserid_message = _loginPage.enter_userid_message.getText();
            Assert.assertTrue(enteruserid_message.contains("Please enter userid"));
            log.info("Validation Successful: User could not login with an empty username");
        } catch (Exception e) {
            log.error("Verification failed, user could login with an empty username: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void IncorrectPassword() throws Exception {

        if (sso_username == null && sso_username.isEmpty() && cregalink_username == null && cregalink_username.isEmpty()) {
            log.error(username_missing_message);
            throw new IllegalStateException("Missing required property: username");
        } else if (sso_password == null && sso_password.isEmpty() && cregalink_password == null && cregalink_password.isEmpty()) {
            log.error(password_missing_message);
            throw new IllegalStateException("Missing required property: {}");
        }

        try {
            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.cregalink_username_textbox));
            _loginPage.cregalink_username_textbox.click();
            _loginPage.cregalink_username_textbox.sendKeys(cregalink_username);
            _loginPage.cregalink_password_textbox.click();
            _loginPage.cregalink_password_textbox.sendKeys("741852");
            _loginPage.cregalink_login_button.click();
            String Invalidmessage = _loginPage.invalid_useridOrpassword_message.getText();
            getWait().until(ExpectedConditions.visibilityOf(_loginPage.invalid_useridOrpassword_message));
            Assert.assertTrue(Invalidmessage.contains("Invalid user or password"));
            log.info("Validation Successful: User could not login with an incorrect password when using password: {}");
        } catch (Exception e) {
            log.error("Validation failed, user could login using an incorrect password: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void loginEmptyPassword() throws Exception {

        if (sso_username == null && sso_username.isEmpty() && cregalink_username == null && cregalink_username.isEmpty()) {
            log.error(username_missing_message);
            throw new IllegalStateException("Missing required property username: {}");
        }

        try {
            getWait().until(ExpectedConditions.elementToBeClickable(_loginPage.cregalink_username_textbox));
            _loginPage.cregalink_username_textbox.click();
            _loginPage.cregalink_username_textbox.sendKeys(cregalink_username);
            _loginPage.cregalink_login_button.click();
            String enteruserid_message = _loginPage.enter_userid_message.getText();
            Assert.assertTrue(enteruserid_message.contains("Please enter userid"));
            log.info("Validation Successful: User could not login with an empty password");
        } catch (Exception e) {
            log.error("Verification failed, user could login with an empty password: {}", e.getMessage(), e);
            throw e;
        }
    }
 }