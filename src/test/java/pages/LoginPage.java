package pages;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class login_page {

    private WebDriver driver;

    @FindBy(id = "i0116")
    public WebElement sso_username_textbox;

    @FindBy(id = "idSIButton9")
    public WebElement sso_next_button;

    @FindBy(id = "i0118")
    public WebElement sso_password_textbox;

    @FindBy(id = "frmlogin:password")
    public WebElement cregalink_password_textbox;

    @FindBy(id = "idSIButton9")
    public WebElement sso_signIn_button;

    @FindBy(id = "frmlogin:username")
    public WebElement cregalink_username_textbox;

    @FindBy(id = "idA_PWD_ForgotPassword")
    public WebElement forgot_password_link;

    @FindBy(id = "frmlogin")
    public WebElement missing_username_message;

    @FindBy(id = "tileList")
    public WebElement mfa_username_textbox;

    @FindBy(id = "oneTimeCodePrimaryButton")
    public WebElement mfa_verify_button;

    @FindBy(id = "frmlogin:btnlogin")
    public WebElement cregalink_login_button;

    @FindBy(id = "j_id_k:j_id_m")
    public WebElement disclaimer_checkbox;

    @FindBy(id = "j_id_12:acceptNew")
    public WebElement accept_disclaimer_button;

    @FindBy(id = "j_id_36")
    public WebElement logout_button;

    @FindBy(xpath = "//span[normalize-space()='Please enter userid']")
    public WebElement enter_userid_message;

    @FindBy(xpath = "//span[normalize-space()='Invalid user or password']")
    public WebElement invalid_useridOrpassword_message;

    public login_page() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is null in login page constructor.");
        }
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
}