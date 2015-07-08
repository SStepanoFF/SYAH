package Pages.Portal;

import framework.utils.CSVOperations;
import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by sergii.stepanov on 02/07/2015.
 */
public class AlertTabPage extends PortalMainPage{

    private String alertID;
    public String getAlertID(){
        return alertID;
    }
    private String nameXpath;
    private String refNmbXpath;
    private String officeXpath;
    private String personTypeXpath;
    private String consultantXpath;
    private String surveyCompletionDateXpath;
    private String alertTypeXpath;
    private String statusXpath;
    private String alertDueDateXpath;
    private String lastModifyDateXpath;

    public AlertTabPage(WebDriver driver) {
        super(driver);
        alertID= DataBase.executeSQLQuery("SELECT ALERT_ID FROM ALERT JOIN RESPONSE ON ALERT.RESPONSE_ID=RESPONSE.RESPONSE_ID WHERE  KS_RESPONDENT_ID= "+ GlobalVariables.getRespondentID(), "ALERT_ID");
        waitForLoadingDisappear();
        waitForAJAXfinish();

        nameXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_name']";
        refNmbXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_refNumber']";
        officeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_assignedTo.officeName']";
        personTypeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_personTypeKeysurvey']";
        consultantXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_consultant']";
        surveyCompletionDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_surveyCompletionDate']";
        alertTypeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_alertType']";
        statusXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_status']";
        alertDueDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_alertDueDate']";
        lastModifyDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_lastModifyDate']";
    }

    @FindBy (id = "name")
    private WebElement filterNameField;
    @FindBy (id = "alertStatus")
    private WebElement filterAlertStatus;
    @FindBy (xpath = "//button[contains(text(),'Search')]")
    private WebElement filterSearchBtn;

    public String getName(){
        return driver.findElement(By.xpath(nameXpath)).getAttribute("title").toLowerCase();
    }
    public String getrefNmb(){
        return driver.findElement(By.xpath(refNmbXpath)).getAttribute("title").toLowerCase();
    }
    public String getOffice(){
        return driver.findElement(By.xpath(officeXpath)).getAttribute("title").toLowerCase();
    }
    public String getPersonType(){
        return driver.findElement(By.xpath(personTypeXpath)).getAttribute("title").toLowerCase();
    }
    public String getConsultant(){
        return driver.findElement(By.xpath(consultantXpath)).getAttribute("title").toLowerCase();
    }
    public String getSurveyComplitionDate(){
        return driver.findElement(By.xpath(surveyCompletionDateXpath)).getAttribute("title");
    }
    public String getAlertType(){
        return driver.findElement(By.xpath(alertTypeXpath)).getAttribute("title").toLowerCase();
    }
    public String getStatus(){
        return driver.findElement(By.xpath(statusXpath)).getAttribute("title").toLowerCase();
    }
    public String getAlertDueDate(){
        return driver.findElement(By.xpath(alertDueDateXpath)).getAttribute("title");
    }
    public String getLastModifyDate(){
        return driver.findElement(By.xpath(lastModifyDateXpath)).getAttribute("title");
    }

    public void searchAlert(){
        filterNameField.sendKeys(CSVOperations.getCellFromAutofillCSVFile(3));
        new Select(filterAlertStatus).selectByValue("");
        filterSearchBtn.click();
        waitForLoadingDisappear();
        waitForAJAXfinish();
    }
}
