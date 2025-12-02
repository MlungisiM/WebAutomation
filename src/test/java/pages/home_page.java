package pages;

import base.base_class;
import configurations.ExtentTestListener;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(ExtentTestListener.class)
public class home_page extends base_class {

    private WebDriver driver;

    @FindBy(id = "j_id_7e_2r:j_id_7e_35")
    public WebElement hide_policy_warning_button;

    @FindBy(id = "j_id_7e_2r:j_id_7e_34")
    public WebElement action_policy_wording_acceptance_link;

    @FindBy(xpath = "//span[normalize-space()='Policy Enquiries']")
    public WebElement policy_enquiries_link;

    @FindBy(xpath = "//span[normalize-space()='Credit Limits']")
    public WebElement credit_limits_link;

    @FindBy(xpath = "//span[normalize-space()='Declarations']")
    public WebElement declarations_link;

    @FindBy(xpath = "//span[normalize-space()='Claims']")
    public WebElement claims_link;

    @FindBy(xpath = "//span[normalize-space()='Actions']")
    public WebElement actions_link;

    @FindBy(xpath = "//span[normalize-space()='Admin Guides']")
    public WebElement admin_guides_link;

    @FindBy(xpath = "//span[normalize-space()='Policy Administration']")
    public WebElement policy_administration_link;

    @FindBy(xpath = "//span[normalize-space()='Help']")
    public WebElement help_link;

    @FindBy(xpath = "//span[normalize-space()='Contact']")
    public WebElement contact_link;

    @FindBy(xpath = "//span[normalize-space()='Credentials']")
    public WebElement credentials_link;

    @FindBy(xpath = "//a[@class='closeit']")
    public WebElement close_list_of_policies_link;

    @FindBy(id = "frmSearchPol:j_id_7e_5")
    public WebElement show_policy_warnings_and_outstanding_actions_button;

    @FindBy(id = "frmSearchPol:searchOption")
    public WebElement policy_search_options_dropdown;

    @FindBy(id = "frmSearchPol:SearchPol")
    public WebElement policy_search_options_textbox;

    @FindBy(id = "frmSearchPol:btnsearch")
    public WebElement policy_search_options_search_button;

    @FindBy(id = "frmSearchPol:dtTbl:0:j_id_7e_15")
    public WebElement policy_search_first_option_results;

    @FindBy(xpath = "//td[normalize-space()='Age Analysis']")
    public WebElement age_analysis_tab;//j_id_4

    @FindBy(id = "frmSearchPol:j_id_7e_n")
    public WebElement next_tab;

    @FindBy(id = "frmSearchPol:j_id_7e_o")
    public WebElement refresh_tab;

    @FindBy(id = "frmSearchPol:dtTbl:0:j_id_7e_t")
    public WebElement limit_quick_link;

    @FindBy(id = "frmSearchPol:dtTbl:0:j_id_7e_v")
    public WebElement declaration_quick_link;

    @FindBy(id = "frmSearchPol:dtTbl:0:j_id_7e_x")
    public WebElement claim_quick_link;


    public home_page() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is null in home page constructor.");
        }
        PageFactory.initElements(driver, this);
    }
}
