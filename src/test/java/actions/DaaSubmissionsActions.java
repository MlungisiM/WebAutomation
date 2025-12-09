package actions;

import base.BaseClass;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.DaaSubmissionsPage;
import pages.HomePage;
import pages.LoginPage;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DaaSubmissionsActions extends BaseClass {

    private LoginPage _loginPage;
    private HomePage _homePage;
    private DaaSubmissionsPage _daaSubmissionsPage;

    String adf_outdoor_policy_no = DriverFactory.prop.getProperty("adf_outdoor_policy_no");

    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        _loginPage = new LoginPage();
        _homePage = new HomePage();
        _daaSubmissionsPage = new DaaSubmissionsPage();
    }


        public void Submit_Valid_Excel_DAA() throws Exception {

            if (adf_outdoor_policy_no == null || adf_outdoor_policy_no.isBlank()) {
                throw new IllegalStateException("Missing property: adf_outdoor_policy_no");
            }

            try{
        Select search_option = new Select(_homePage.policy_search_options_dropdown);
        search_option.selectByVisibleText("Policy No");
        _homePage.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
        _homePage.policy_search_options_search_button.click();
        getWait().until(ExpectedConditions.visibilityOf(_homePage.policy_search_first_option_results));
        _homePage.policy_search_first_option_results.click();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(_homePage.declarations_link).perform();
        _homePage.age_analysis_tab.click();
        _daaSubmissionsPage.date_of_extraction_textbox.click();
        _daaSubmissionsPage.date_picker_today.click();
        Select period = new Select(_daaSubmissionsPage.reporting_period_dropdown);
        period.selectByIndex(1);
        Select Apackage = new Select(_daaSubmissionsPage.accounting_package_dropdown);
        Apackage.selectByIndex(1);
        Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.xlsx");
        String absolutePath = filePath.toAbsolutePath().toString();
        WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(absolutePath);
        _daaSubmissionsPage.upload_button.click();
        getWait().until(ExpectedConditions.visibilityOf(_daaSubmissionsPage.document_uploaded_successfully_message));
        Assert.assertTrue(_daaSubmissionsPage.document_uploaded_successfully_message.isDisplayed());
        log.info("DAA excel file submitted successfully");
        } catch (Exception e) {
            log.error("DAA excel file submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_Valid_CSV_DAA() throws Exception {
        try{
            Select search_option = new Select(_homePage.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _homePage.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _homePage.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_homePage.policy_search_first_option_results));
            _homePage.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_homePage.declarations_link).perform();
            _homePage.age_analysis_tab.click();
            _daaSubmissionsPage.date_of_extraction_textbox.click();
            _daaSubmissionsPage.date_picker_today.click();
            Select period = new Select(_daaSubmissionsPage.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daaSubmissionsPage.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.csv");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daaSubmissionsPage.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daaSubmissionsPage.document_uploaded_successfully_message));
            Assert.assertTrue(_daaSubmissionsPage.document_uploaded_successfully_message.isDisplayed());
            log.info("DAA csv file submitted successfully");
        } catch (Exception e) {
            log.error("DAA csv submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_Valid_PDF_DAA() throws Exception {
        try{
            Select search_option = new Select(_homePage.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _homePage.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _homePage.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_homePage.policy_search_first_option_results));
            _homePage.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_homePage.declarations_link).perform();
            _homePage.age_analysis_tab.click();
            _daaSubmissionsPage.date_of_extraction_textbox.click();
            _daaSubmissionsPage.date_picker_today.click();
            Select period = new Select(_daaSubmissionsPage.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daaSubmissionsPage.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.pdf");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daaSubmissionsPage.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daaSubmissionsPage.document_uploaded_successfully_message));
            Assert.assertTrue(_daaSubmissionsPage.document_uploaded_successfully_message.isDisplayed());
            log.info("DAA pdf file submitted successfully");
        } catch (Exception e) {
            log.error("DAA pdf submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_a_jpg_DAA() throws Exception {
        try{
            Select search_option = new Select(_homePage.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _homePage.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _homePage.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_homePage.policy_search_first_option_results));
            _homePage.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_homePage.declarations_link).perform();
            _homePage.age_analysis_tab.click();
            _daaSubmissionsPage.date_of_extraction_textbox.click();
            _daaSubmissionsPage.date_picker_today.click();
            Select period = new Select(_daaSubmissionsPage.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daaSubmissionsPage.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.jpg");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daaSubmissionsPage.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daaSubmissionsPage.document_uploaded_successfully_message));
            Assert.assertTrue(_daaSubmissionsPage.document_uploaded_successfully_message.isDisplayed());
            log.info("Test Passed: DAA jpeg file was uploaded successfully");
        } catch (Exception e) {
            log.error("Test Failed: DAA jpg submission was rejected", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_a_txt_DAA() throws Exception {
        try{
            Select search_option = new Select(_homePage.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _homePage.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _homePage.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_homePage.policy_search_first_option_results));
            _homePage.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_homePage.declarations_link).perform();
            _homePage.age_analysis_tab.click();
            _daaSubmissionsPage.date_of_extraction_textbox.click();
            _daaSubmissionsPage.date_picker_today.click();
            Select period = new Select(_daaSubmissionsPage.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daaSubmissionsPage.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.txt");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daaSubmissionsPage.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daaSubmissionsPage.document_uploaded_successfully_message));
            Assert.assertTrue(_daaSubmissionsPage.document_uploaded_successfully_message.isDisplayed());
            log.info("Test Passed: DAA .txt file was uploaded successfully");

            System.out.println("Driver in Page Object: " + getDriver().getClass().getName());
        } catch (Exception e) {
            log.error("Test Failed: DAA .txt submission was rejected", e.getMessage(), e);
            throw e;
        }
    }

 }