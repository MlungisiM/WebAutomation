package pages;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DaaServicePage {

    private WebDriver driver;

    @FindBy(id = "i0116")
    public WebElement username_textbox;

    @FindBy(id = "i0118")
    public WebElement password_textbox;

    @FindBy(id = "idSIButton9")
    public WebElement next_and_signin_button;

    @FindBy(xpath = "//span[normalize-space()='Login']")
    public WebElement login_button;

    @FindBy(xpath = "//span[normalize-space()='Home']")
    public WebElement home_menu;

    @FindBy(xpath = "//span[normalize-space()='API']")
    public WebElement api_menu;

    @FindBy(xpath = "//span[normalize-space()='Submissions']")
    public WebElement submissions_menu;

    @FindBy(xpath = "//span[normalize-space()='Reports']")
    public WebElement reports_menu;

    @FindBy(xpath = "//span[normalize-space()='Matching']")
    public WebElement matching_menu;

    @FindBy(xpath = "//span[normalize-space()='Entities']")
    public WebElement entities_menu;

    @FindBy(xpath = "//span[normalize-space()='Administration']")
    public WebElement administrations_menu;

    @FindBy(xpath = "//span[normalize-space()='Administration']")
    public WebElement logout_button;

    @FindBy(xpath = "//*[@id=\"pn_id_1-table\"]/tbody/tr[1]/td[9]")
    public WebElement failure_reason_first_row;

    public DaaServicePage() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is null in home page constructor.");
        }
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
}
