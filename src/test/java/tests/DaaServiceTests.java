package tests;

import actions.DaaServiceActions;
import base.BaseClass;
import utilities.ExtentTestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestListener.class)
public class DaaServiceTests extends BaseClass {

    DaaServiceActions _DaaServiceActions;

    @BeforeMethod()
    public void initPages() throws Exception {
        super.setUp();          // ✅ This initializes WebDriver properly
        _DaaServiceActions = new DaaServiceActions();
        _DaaServiceActions.init();           // ✅ Now getDriver() is not null
    }

    @Test(testName = "Login Successfully")
    public void Login_Successfully() throws Exception {_DaaServiceActions.LoginDaaServiceSuccessfully();}
}
