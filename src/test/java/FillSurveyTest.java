import Pages.Surveys.SurveyPage;
import framework.utils.ExcelOperations;
import framework.utils.GlobalVariables;
import framework.webDriver.Driver;
import org.testng.annotations.*;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 30/06/2015.
 */
public class FillSurveyTest extends BaseTest {

    private SurveyPage surveyPage;

    @BeforeClass
    public void setup(){
        GlobalVariables.setSurveyUrl(ExcelOperations.getLinkFromExcelFile());
        driver= Driver.getInstance();
        driver.navigate().to(GlobalVariables.getSurveyUrl());
        surveyPage=new SurveyPage(driver);
    }

    @Parameters ({"answer_Q6", "answerQ9_Q10_Q11"})
    @Test
    private void fillSurvey(@Optional("1") int answer_Q6, boolean answerQ9_Q10_Q11){
        surveyPage.fillSurvey(answer_Q6, answerQ9_Q10_Q11);
        System.out.println("ALERT type: "+GlobalVariables.getAlertType());
    }

    @AfterClass
    private void switchTab(){
        surveyPage.switchWindow(driver,1);
    }
}
