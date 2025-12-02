package actions;

import base.base_class;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.daa_submissions_page;
import pages.home_page;
import pages.login_page;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class daa_submissions_actions extends base_class {

    private login_page _login_page;
    private home_page _home_page;
    private daa_submissions_page _daa_submissions_page;

    String adf_outdoor_policy_no = DriverFactory.prop.getProperty("adf_outdoor_policy_no.");

    public void init() {
        if (getDriver() == null) {
            log.error("WebDriver is not initialized.");
            throw new IllegalStateException("WebDriver is not initialized.");
        }

        _login_page = new login_page();
        _home_page = new home_page();
        _daa_submissions_page = new daa_submissions_page();
    }


        public void Submit_Valid_Excel_DAA() throws Exception {
        try{
        Select search_option = new Select(_home_page.policy_search_options_dropdown);
        search_option.selectByVisibleText("Policy No");
        _home_page.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
        _home_page.policy_search_options_search_button.click();
        getWait().until(ExpectedConditions.visibilityOf(_home_page.policy_search_first_option_results));
        _home_page.policy_search_first_option_results.click();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(_home_page.declarations_link).perform();
        _home_page.age_analysis_tab.click();
        _daa_submissions_page.date_of_extraction_textbox.click();
        _daa_submissions_page.date_picker_today.click();
        Select period = new Select(_daa_submissions_page.reporting_period_dropdown);
        period.selectByIndex(1);
        Select Apackage = new Select(_daa_submissions_page.accounting_package_dropdown);
        Apackage.selectByIndex(1);
        Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.xlsx");
        String absolutePath = filePath.toAbsolutePath().toString();
        WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(absolutePath);
        _daa_submissions_page.upload_button.click();
        getWait().until(ExpectedConditions.visibilityOf(_daa_submissions_page.document_uploaded_successfully_message));
        Assert.assertTrue(_daa_submissions_page.document_uploaded_successfully_message.isDisplayed());
        log.info("DAA excel file submitted successfully");
        } catch (Exception e) {
            log.error("DAA excel file submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_Valid_CSV_DAA() throws Exception {
        try{
            Select search_option = new Select(_home_page.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _home_page.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _home_page.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_home_page.policy_search_first_option_results));
            _home_page.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_home_page.declarations_link).perform();
            _home_page.age_analysis_tab.click();
            _daa_submissions_page.date_of_extraction_textbox.click();
            _daa_submissions_page.date_picker_today.click();
            Select period = new Select(_daa_submissions_page.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daa_submissions_page.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.csv");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daa_submissions_page.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daa_submissions_page.document_uploaded_successfully_message));
            Assert.assertTrue(_daa_submissions_page.document_uploaded_successfully_message.isDisplayed());
            log.info("DAA csv file submitted successfully");
        } catch (Exception e) {
            log.error("DAA csv submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_Valid_PDF_DAA() throws Exception {
        try{
            Select search_option = new Select(_home_page.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _home_page.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _home_page.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_home_page.policy_search_first_option_results));
            _home_page.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_home_page.declarations_link).perform();
            _home_page.age_analysis_tab.click();
            _daa_submissions_page.date_of_extraction_textbox.click();
            _daa_submissions_page.date_picker_today.click();
            Select period = new Select(_daa_submissions_page.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daa_submissions_page.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.pdf");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daa_submissions_page.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daa_submissions_page.document_uploaded_successfully_message));
            Assert.assertTrue(_daa_submissions_page.document_uploaded_successfully_message.isDisplayed());
            log.info("DAA pdf file submitted successfully");
        } catch (Exception e) {
            log.error("DAA pdf submission failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_a_jpg_DAA() throws Exception {
        try{
            Select search_option = new Select(_home_page.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _home_page.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _home_page.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_home_page.policy_search_first_option_results));
            _home_page.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_home_page.declarations_link).perform();
            _home_page.age_analysis_tab.click();
            _daa_submissions_page.date_of_extraction_textbox.click();
            _daa_submissions_page.date_picker_today.click();
            Select period = new Select(_daa_submissions_page.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daa_submissions_page.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.jpg");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daa_submissions_page.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daa_submissions_page.document_uploaded_successfully_message));
            Assert.assertTrue(_daa_submissions_page.document_uploaded_successfully_message.isDisplayed());
            log.info("Test Passed: DAA jpeg file was uploaded successfully");
        } catch (Exception e) {
            log.error("Test Failed: DAA jpg submission was rejected", e.getMessage(), e);
            throw e;
        }
    }

    public void Submit_a_txt_DAA() throws Exception {
        try{
            Select search_option = new Select(_home_page.policy_search_options_dropdown);
            search_option.selectByVisibleText("Policy No");
            _home_page.policy_search_options_textbox.sendKeys(adf_outdoor_policy_no);
            _home_page.policy_search_options_search_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_home_page.policy_search_first_option_results));
            _home_page.policy_search_first_option_results.click();
            Actions actions = new Actions(getDriver());
            actions.moveToElement(_home_page.declarations_link).perform();
            _home_page.age_analysis_tab.click();
            _daa_submissions_page.date_of_extraction_textbox.click();
            _daa_submissions_page.date_picker_today.click();
            Select period = new Select(_daa_submissions_page.reporting_period_dropdown);
            period.selectByIndex(1);
            Select Apackage = new Select(_daa_submissions_page.accounting_package_dropdown);
            Apackage.selectByIndex(1);
            Path filePath = Paths.get("src", "test", "resources", "Mlu's DAA Submission.txt");
            String absolutePath = filePath.toAbsolutePath().toString();
            WebElement fileInput = getDriver().findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys(absolutePath);
            _daa_submissions_page.upload_button.click();
            getWait().until(ExpectedConditions.visibilityOf(_daa_submissions_page.document_uploaded_successfully_message));
            Assert.assertTrue(_daa_submissions_page.document_uploaded_successfully_message.isDisplayed());
            log.info("Test Passed: DAA .txt file was uploaded successfully");
        } catch (Exception e) {
            log.error("Test Failed: DAA .txt submission was rejected", e.getMessage(), e);
            throw e;
        }
    }

 }