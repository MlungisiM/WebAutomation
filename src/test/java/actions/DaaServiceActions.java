package actions;

import base.BaseClass;
import org.testng.Assert;
import pages.DaaServicePage;

public class DaaServiceActions extends BaseClass {

    private DaaServicePage _DaaServicePage;


    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        _DaaServicePage = new DaaServicePage();
    }

    public void LoginDaaServiceSuccessfully() throws Exception {
        try{
            _DaaServicePage.login_button.click();

            Assert.assertTrue(_DaaServicePage.logout_button.isDisplayed());
            log.info("User logged in successfully.");
            log.info("Page title is: "+getDriver().getTitle());
        } catch (Exception e) {
            log.error("User failed to login");
            throw e;
        }
    }


        public void VerifySuccessfulSubmission() throws Exception {
        try{
            _DaaServicePage.login_button.click();
            _DaaServicePage.submissions_menu.click();
            System.out.println("Failure reason is: " + _DaaServicePage.failure_reason_first_row.getText());
            log.info("DAA file failed processing");
        } catch (Exception e) {
            log.error("DAA file failed processing: {}", e.getMessage(), e);
            throw e;
        }
    }
 }