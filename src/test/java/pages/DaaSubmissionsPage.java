package pages;

import utilities.ExtentTestListener;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(ExtentTestListener.class)
public class DaaSubmissionsPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Age Analysis Submission']")
    public WebElement age_analysis_submission_heading;

    @FindBy(id = "frmSearchPol:j_id_7e_5")
    public WebElement show_policy_warnings_and_outstanding_actions_button;

    @FindBy(id = "frmSubmissions")
    public WebElement choose_file_button;

    @FindBy(id = "frmSubmissions:popupCalEnd_input")
    public WebElement date_of_extraction_textbox;

    @FindBy(id = "frmSubmissions:selRepPeriod")
    public WebElement reporting_period_dropdown;

    @FindBy(name = "frmSubmissions:j_id_8m_w")
    public WebElement password_textbox;

    @FindBy(name = "frmSubmissions:j_id_8m_y")
    public WebElement reinput_password_textbox;

    @FindBy(id = "frmSubmissions:selAccPac")
    public WebElement accounting_package_dropdown;

    @FindBy(id = "frmSubmissions:j_id_8z_z")
    public WebElement upload_button;

    @FindBy(id = "frmSubmissions:j_id_8m_1o")
    public WebElement export_to_excel_button;

    @FindBy(id = "growl_container")
    public WebElement document_uploaded_successfully_message;

    @FindBy(id = "ui-datepicker-div")
    public WebElement date_picker;

    @FindBy(css = ".ui-datepicker-calendar td.ui-datepicker-today a")
    public WebElement date_picker_today;

    @FindBy(css = ".ui-blockui, .ui-overlay, .ui-dialog-mask, .ui-widget-overlay, .ui-loading, .ajax-loading, .modal-backdrop")
    public WebElement overlays;

    @FindBy(xpath = "//*[@id=\"growl_container\"]/div[1]/div")
    public WebElement valid_formats_message;

    @FindBy(xpath = "//*[@id=\"growl_container\"]/div[2]/div/div[2]/span")
    public WebElement select_a_valid_document_message;

    public DaaSubmissionsPage() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is null in home page constructor.");
        }
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
}
