package KS;

import Pages.KS.KSLoginPage;
import Pages.KS.KSMainPage;
import Pages.KS.KSManageResponsePage;
import framework.webDriver.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 12/06/2015.
 */
public class KSOperationsTests extends BaseTest {

    private KSLoginPage ksLoginPage;
    private KSMainPage ksMainPage;
    private KSManageResponsePage ksManageResponsePage;

    @BeforeClass
    public void setup(){
        driver= Driver.getInstance();
    }

    @Test (priority = 1)
    private void ksLoginTest(){
        ksLoginPage=new KSLoginPage(driver);
        ksMainPage=ksLoginPage.loginKS();
    }

    @Test (priority = 2)
    private void getContactExcelFileTest(){
        ksManageResponsePage=ksMainPage.openManageResponsePage();
        ksManageResponsePage.exportContactsToExcel();
    }

//    @Test
//    private void reportVerificationTest(){}

}
