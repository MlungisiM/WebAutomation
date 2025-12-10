package tests;

import actions.DaaSubmissionsActions;
import actions.CregalinkLoginActions;
import base.BaseClass;
import configurations.ExtentTestListener;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ExtentTestListener.class)
public class DaaSubmissionsTests extends BaseClass {

    DaaSubmissionsActions submissions;
    CregalinkLoginActions login;


    @BeforeMethod(alwaysRun = true)
    public void initPages() throws Exception {
        super.setUp();           // initializes driver & props
        login = new CregalinkLoginActions();
        login.init();            // must be called

        submissions = new DaaSubmissionsActions();
        submissions.init();      // must be called or you'll get NPEs
    }


    @Test(testName = "Submit a valid Excel DAA file")
        public void Valid_Excel_File_Submission() throws Exception {
        login.loginValidUsername();
        submissions.Submit_Valid_Excel_DAA();}

    @Test(testName = "Submit a valid CSV DAA file")
        public void Valid_CSV_File_Submission() throws Exception {
        login.loginValidUsername();
        submissions.Submit_Valid_CSV_DAA();}

    @Test(testName = "Submit a valid PDF DAA file")
        public void Valid_PDF_File_Submission() throws Exception {
        login.loginValidUsername();
        submissions.Submit_Valid_PDF_DAA();}

    @Test(testName = "Submit a JPG DAA file")
        public void JPG_Submission() throws Exception {
        login.loginValidUsername();
        submissions.Submit_a_jpg_DAA();}

    @Test(testName = "Submit a .txt DAA file")
    public void txt_Submission() throws Exception {
        login.loginValidUsername();
        submissions.Submit_a_txt_DAA();}
    }
