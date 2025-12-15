package pages;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

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

    @FindBy(id = "j_id_o:j_id_q")
    public WebElement disclaimer_checkbox;

    @FindBy(id = "j_id_16:acceptNew")
    public WebElement accept_disclaimer_button;

    @FindBy(id = "j_id_3d")
    public WebElement logout_button;

    @FindBy(xpath = "//span[normalize-space()='Please enter userid']")
    public WebElement enter_userid_message;

    @FindBy(xpath = "//span[normalize-space()='Invalid user or password']")
    public WebElement invalid_useridOrpassword_message;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is null in login page constructor.");
        }
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
}