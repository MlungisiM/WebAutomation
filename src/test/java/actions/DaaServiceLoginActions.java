package actions;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import pages.DaaServicePage;

public class DaaServiceLoginActions extends BaseClass {

        private DaaServicePage _DaaServicePage;

    @BeforeMethod
    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        _DaaServicePage = new DaaServicePage();
    }

    public void LoginDaaServiceSuccessfully() throws Exception {

        try {
            _DaaServicePage.login_button.click();
            Assert.assertTrue(_DaaServicePage.logout_button.isDisplayed());
            log.info("User logged in the DAA Service successfully");
        } catch (Exception e) {
            log.error("Login to Daa Service failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}
