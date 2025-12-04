package tests;

import actions.CregalinkLoginActions;
import base.BaseClass;
import configurations.ExtentTestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestListener.class)
public class LoginTests extends BaseClass {

    CregalinkLoginActions login;

    @BeforeMethod()
    public void initPages() throws Exception {
        super.setUp();          // ✅ This initializes WebDriver properly
        login = new CregalinkLoginActions();
        login.init();           // ✅ Now getDriver() is not null
    }

    @Test(testName = "Login Successfully")
    public void Login_Successfully() throws Exception {login.loginValidUsername();}

    @Test(testName = "Login with incorrect username")
    public void Login_using_Incorrect_Username() throws Exception {login.IncorrectUsername();}
    
    @Test(testName = "Login with empty username")
    public void Login_using_Empty_Username() throws Exception {login.loginEmptyUsername();}

    @Test(testName = "Login with an incorrect username")
    public void Login_using_Incorrect_Password() throws Exception {login.IncorrectPassword();}

    @Test(testName = "Login with an empty password")
    public void Login_using_Empty_Password() throws Exception {login.loginEmptyPassword();}
}