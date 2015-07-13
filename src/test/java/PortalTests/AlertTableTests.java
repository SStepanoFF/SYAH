package PortalTests;

import Pages.Portal.AlertTabPage;
import Pages.Portal.LoginPortalPage;
import Pages.Portal.PortalMainPage;
import framework.DriverOperations;
import framework.utils.CSVOperations;
import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import framework.webDriver.Driver;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.sql.Timestamp.valueOf;

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
        DriverOperations.createNewWindow(driver, GlobalVariables.PortalUrl);
        LoginPortalPage loginPortalPage=new LoginPortalPage(driver);
        loginPortalPage.loginPortal();
    }

    @Test (priority = 2)
    private void openAlertTab(){
        portalMainPage= new PortalMainPage(driver);
        portalMainPage.openAlertTabPage();
    }

    @Test (priority = 3)
    private void nameVerificationTest(){
        alertTabPage=new AlertTabPage(driver);
        alertTabPage.searchAlert();
        Assert.assertEquals(alertTabPage.getName(), CSVOperations.getCellFromAutofillCSVFile(3));
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
        Assert.assertEquals(alertTabPage.getPersonType().toLowerCase(), CSVOperations.getPersonTypeFromCSVFile());
    }

    @Test (priority = 7)
    private void consultantVerificationTest(){
        Assert.assertEquals(alertTabPage.getConsultant(),CSVOperations.getCellFromAutofillCSVFile(21));
    }

    @Test (priority = 8)
    private void surveyCompleteVerificationTest(){
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH");
//        Date date = new Date();
        Assert.assertEquals(alertTabPage.getSurveyComplitionDate(), DataBase.executeSQLQuery("Select SURVEY_COMPLETION_DATE from ALERT where ALERT_ID= " + alertTabPage.getAlertID(), "SURVEY_COMPLETION_DATE"));  //
    }

    @Test (priority = 9)
    private void alertTypeVerificationTest(){
        System.out.println("Portal Alert type: "+alertTabPage.getAlertType()+";  Survey Alert type: "+GlobalVariables.getAlertType());
        Assert.assertTrue(alertTabPage.getAlertType().contains(GlobalVariables.getAlertType()));
    }

    @Test (priority = 10)
    private void statusVerificationTest(){
        Assert.assertEquals(alertTabPage.getStatus(),GlobalVariables.getAlertStatus());
    }

    @Test (priority = 11)
    private void alertDueDateVerificationTest(){
        Assert.assertEquals(alertTabPage.getAlertDueDate(), DataBase.executeSQLQuery("Select ALERT_DUE_DATE from ALERT where ALERT_ID= " + alertTabPage.getAlertID(), "ALERT_DUE_DATE"));  //contains(dateFormat.format(date))
    }

    @Test (priority = 12)
    private void alertModifiedDateVerificationTest(){
        Assert.assertEquals(alertTabPage.getLastModifyDate(), DataBase.executeSQLQuery("Select LAST_MODIFY_DATE from ALERT where ALERT_ID= " + alertTabPage.getAlertID(), "LAST_MODIFY_DATE"));  //contains(dateFormat.format(date))
    }

    @AfterClass
    private void switchTab(){
        DriverOperations.switchWindow(driver,0);
    }

}
