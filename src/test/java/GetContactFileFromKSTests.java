import Pages.KS.KSLoginPage;
import Pages.KS.KSMainPage;
import Pages.KS.KSManageResponsePage;
import framework.webDriver.Driver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 12/06/2015.
 */
public class GetContactFileFromKSTests extends BaseTest {

    private KSLoginPage ksLoginPage;
    private KSMainPage ksMainPage;
    private KSManageResponsePage ksManageResponsePage;

    @BeforeClass
    public void setup(){
        driver= Driver.getInstance();
        ksLoginPage=new KSLoginPage(driver);
    }

    @Test
    private void getContactExcelFileTest(){
        ksMainPage=ksLoginPage.loginKS();
        ksManageResponsePage=ksMainPage.openManageResponsePage();
        ksManageResponsePage.exportContactsToExcel();
    }

//    @Test
//    private void getSurvURLfromExcelFile(){
//        GlobalVariables.setSurveyUrl(ExcelOperations.getLinkFromExcelFile());
//        System.out.println("LINK: "+GlobalVariables.getSurveyUrl());
//    }
}
