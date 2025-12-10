package tests;

import base.BaseClass;
import configurations.DbUtils;
import configurations.ExtentTestListener;
import factory.DriverFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static actions.DbActions.printAllSubmissions;

@Listeners(ExtentTestListener.class)
public class DbTests extends BaseClass {

    @Test
    public void PrintAllSubmissions() throws Exception {
        printAllSubmissions();
    }
}
