package PortalTests;

import Pages.Portal.AlertTabPage;
import Pages.Portal.LoginPortalPage;
import Pages.Portal.PortalMainPage;
import framework.utils.CSVOperations;
import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import framework.webDriver.Driver;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergii.stepanov on 03/07/2015.
 */
public class AlertTableTests extends BaseTest {

    private PortalMainPage portalMainPage;
    private AlertTabPage alertTabPage;

    @BeforeClass
    public void setup(){
        driver= Driver.getInstance();
    }

    @Test (priority = 1)
    private void loginPortalTest(){
        LoginPortalPage loginPortalPage=new LoginPortalPage(driver);
        loginPortalPage.loginPortal();
    }

    @Test (priority = 2)
    private void openAlertTab(){
        portalMainPage= new PortalMainPage(driver);
        alertTabPage=portalMainPage.openAlertTabPage();
        alertTabPage.searchAlert();
    }

    @Test (priority = 3)
    private void nameVerificationTest(){
        Assert.assertEquals(alertTabPage.getName(),CSVOperations.getCellFromAutofillCSVFile(3));
    }

    @Test (priority = 4)
    private void refNmbVerificationTest(){
        Assert.assertEquals(alertTabPage.getrefNmb(),CSVOperations.getCellFromAutofillCSVFile(2));
    }

    @Test (priority = 5)
    private void officeVerificationTest(){
        Assert.assertEquals(alertTabPage.getOffice(),CSVOperations.getCellFromAutofillCSVFile(23));
    }

    @Test (priority = 6)
    private void personTypeVerificationTest(){
        Assert.assertEquals(alertTabPage.getPersonType(),CSVOperations.getCellFromAutofillCSVFile(6));
    }

    @Test (priority = 7)
    private void consultantVerificationTest(){
        Assert.assertEquals(alertTabPage.getConsultant(),CSVOperations.getCellFromAutofillCSVFile(21));
    }

    @Test (priority = 8)
    private void surveyCompleteVerificationTest(){
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH");
//        Date date = new Date();
        Assert.assertEquals(alertTabPage.getSurveyComplitionDate(),DataBase.executeSQLQuery("Select SURVEY_COMPLETION_DATE from ALERT where ALERT_ID= "+alertTabPage.getAlertID(),"SURVEY_COMPLETION_DATE"));  //contains(dateFormat.format(date))
    }

    @Test (priority = 9)
    private void alertTypeVerificationTest(){
        Assert.assertTrue(alertTabPage.getAlertType().toLowerCase().contains(GlobalVariables.getAlertType()));
    }

    @Test (priority = 10)
    private void statusVerificationTest(){
        Assert.assertEquals(alertTabPage.getStatus(),CSVOperations.getCellFromAutofillCSVFile(21));
    }

}
