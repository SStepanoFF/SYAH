package Pages.Surveys;

import framework.DriverOperations;
import framework.utils.CSVOperations;
import framework.utils.GlobalVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by sergii.stepanov on 30/06/2015.
 */
public class SurveyPage extends DriverOperations {

    private String personType;
    private void setPersonType(){
        String _personType= CSVOperations.getCellFromAutofillCSVFile(6).toLowerCase();
        if (_personType.contains("candidate") || _personType.contains("contact")) {
            personType=_personType;
        } else {
                throw new RuntimeException("Invalid role type!");
        }
    }

    public SurveyPage(WebDriver driver) {
        super(driver);
        setPersonType();
//        driver.get(GlobalVariables.getSurveyUrl());
        wait.until(ExpectedConditions.visibilityOf(nextPgBtn));
    }

    @FindBy (id = "goNextPage")
    private WebElement nextPgBtn;
    @FindBy (id = "goPrevPage")
    private WebElement prevPageBtn;
    @FindBy (css = "input[title='5']")
    private List<WebElement> answerNmb5List;
    @FindBy (tagName = "textarea")
    private WebElement textField_Q7;
    @FindBy (css = "input[title='Name']")
    private WebElement nameField_Q9;
    @FindBy (css = "input[title='Telephone Number']")
    private WebElement telNumbField_Q9;
    @FindBy (id = "AnswerLabel20400753_102209403")
    private WebElement yesAnswer_Q10_Q11;
    @FindBy (id = "AnswerElementTd20400753_102209404")
    private WebElement noAnswer_Q10_Q11;
    @FindBy (id = "goSubmitPage")
    private WebElement submitSurveyBtn;

    @FindBy (css = "input[name='RID']")
    private WebElement respondID;
    @FindBy (id = "inputtextother20400745_102209326")
    private WebElement surveyID;
    @FindBy (id = "inputtextother20400745_102209331")
    private WebElement personTypeElem;

    //region Alerts elements
    @FindBy (id = "RC20400755_102209407")
    private WebElement apacCandFeedback_Q12_A1;
    @FindBy (id = "RC20400755_102209408")
    private WebElement apacClientFeedback_Q12_A2;
    @FindBy (id = "RC20400755_102209409")
    private WebElement clientCallBack_Q12_A3;
    @FindBy (id = "RC20400755_102209410")
    private WebElement clientLowHPIScore_Q12_A4;
    @FindBy (id = "RC20400755_102209411")
    private WebElement candCallBack_Q12_A5;
    @FindBy (id = "RC20400755_102209412")
    private WebElement candSurveyAnswer_Q12_A6;
    @FindBy (id = "RC20400755_102209413")
    private WebElement businessDevelopment_Q12_A7;
    @FindBy (id = "RC20400755_102209414")
    private WebElement clientSurveyAnswer_Q12_A8;
    @FindBy (id = "RC20400755_102209415")
    private WebElement referralAlert_Q12_A9;
    @FindBy (id = "RC20400755_102209416")
    private WebElement candidateSurveyAnswer_Q12_A10;
    //endregion


    // Complete survey Elements
    @FindBy (css = "button[title='Click here to view the previous respondent']")
    private WebElement prefPageBtn;
    @FindBy (css = "button[title='Click here to view the last response']")
    private WebElement lastPageBtn;
    @FindBy (className = "FormSelectBox")
    private WebElement pageNmbField;

    private void answerTo_Q6(int numberOfAnswer){
        driver.findElement(By.cssSelector("input[title='"+numberOfAnswer+"']")).click();
    }

    private void setAnswerNbr5(){
        for (WebElement answer : answerNmb5List){
            answer.click();
        }
    }

    private void fillAnswer_Q9(){
            nameField_Q9.sendKeys("Autotest Name");
            telNumbField_Q9.sendKeys("12345");
    }

    private void setAlertTypeAndStatus(String alertType){
        if (personType.contains("candidate")) {
            GlobalVariables.setAlertType("candidate "+alertType);
        } else {
            GlobalVariables.setAlertType("client "+alertType);
        }

        if (alertType.equals("feedback form") || alertType.equals("survey answer")){
            GlobalVariables.setAlertStatus("closed");
        }else {
            GlobalVariables.setAlertStatus("open");
        }
    }

    public String getRespondentID(){
        return respondID.getAttribute("value");
    }

    public void fillSurvey(int numberOfAnswer_Q6, boolean fill_Q9_Q10_Q11) {
        setAnswerNbr5();   //Q3 answer  Overall, how satisfied or dissatisfied are you with the service provided by Hays?
        nextPgBtn.click();
        setAnswerNbr5();   // Q4 or Q5 answer   Please tell us how satisfied or dissatisfied you are with the following:
        nextPgBtn.click();
        answerTo_Q6(numberOfAnswer_Q6);  // Q6 answer   How likely is it that you would recommend Hays to a friend or colleague?
        nextPgBtn.click();
        waitForAJAXfinished();
        textField_Q7.sendKeys("text");  //  Q7 answer   Please tell us a little more about your experience with Hays or what more we could do to meet your needs to improve your experience with Hays?

        int language = Integer.parseInt(parsTextByRegEx(CSVOperations.getCellFromAutofillCSVFile(0), "\\d"));   // detect language
        if (language == 32772 || language == 3081 || language == 5129 || language == 17) {
            setAlertTypeAndStatus("feedback form");
        }else {    //if (language == 9 || language == 6 || language == 1033 || language == 4105 || language == 6153 || language == 7177 || language == 13321 || language == 3084 || language == 29)
            if (numberOfAnswer_Q6 <= 6) {
                if (fill_Q9_Q10_Q11) {
                    fillAnswer_Q9();
                    setAlertTypeAndStatus("call-back request");
                }else {
                    if (personType.contains("candidate")) {
                        GlobalVariables.setAlertType("candidate survey answer");
                        GlobalVariables.setAlertStatus("close");
                    } else {
                        GlobalVariables.setAlertType("client low HPI score");
                        GlobalVariables.setAlertStatus("open");
                    }
                }
            }else {
                if (fill_Q9_Q10_Q11){   //  Do you know anyone who could use our services?
                    yesAnswer_Q10_Q11.click();
                    if (personType.contains("candidate")) {
                        setAlertTypeAndStatus("referral");
                    } else {
                        GlobalVariables.setAlertType("business development");
                        GlobalVariables.setAlertStatus("open");
                    }
                }else {
                    noAnswer_Q10_Q11.click();
                    setAlertTypeAndStatus("survey answer");
                }
            }
        }
        GlobalVariables.setRespondentID(respondID.getAttribute("value"));
        submitSurveyBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getCompleteSurveyInformation(){
        GlobalVariables.setRespondentID(respondID.getAttribute("value"));
        GlobalVariables.setSurveyID(surveyID.getText());
        personType=personTypeElem.getText();

    }

//    private boolean alertTypeSurveyVerification(){
//        int language = Integer.parseInt(parsTextByRegEx(CSVOperations.getCellFromAutofillCSVFile(0), "\\d"));   // detect language
//        if (language == 32772 || language == 3081 || language == 5129 || language == 17) {
//            setAlertTypeAndStatus("feedback form");
//        }else {    //if (language == 9 || language == 6 || language == 1033 || language == 4105 || language == 6153 || language == 7177 || language == 13321 || language == 3084 || language == 29)
//            if (numberOfAnswer_Q6 <= 6) {
//                if (fill_Q9_Q10_Q11) {
//                    fillAnswer_Q9();
//                    setAlertTypeAndStatus("call-back request");
//                }else {
//                    if (personType.contains("candidate")) {
//                        GlobalVariables.setAlertType("candidate survey answer");
//                        GlobalVariables.setAlertStatus("close");
//                    } else {
//                        GlobalVariables.setAlertType("client low HPI score");
//                        GlobalVariables.setAlertStatus("open");
//                    }
//                }
//            }else {
//                if (fill_Q9_Q10_Q11){   //  Do you know anyone who could use our services?
//                    yesAnswer_Q10_Q11.click();
//                    if (personType.contains("candidate")) {
//                        setAlertTypeAndStatus("referral");
//                    } else {
//                        GlobalVariables.setAlertType("business development");
//                        GlobalVariables.setAlertStatus("open");
//                    }
//                }else {
//                    noAnswer_Q10_Q11.click();
//                    setAlertTypeAndStatus("survey answer");
//                }
//            }
//    }

}
