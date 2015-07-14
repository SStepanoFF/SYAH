package Pages.Portal;

import framework.utils.CSVOperations;
import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        driver.navigate().refresh();
        alertID= GlobalVariables.getAlertID();
        waitForLoadingDisappear();
        waitForAJAXfinished();
    }

    @FindBy (id = "name")
    private WebElement filterNameField;
    @FindBy (id = "alertStatus")
    private WebElement filterAlertStatus;
    @FindBy (css = "button[onclick='gridReload();']")
    private WebElement filterSearchBtn;

    @FindBy (xpath = "//table[@class='table ']//td[1]")
    private List<WebElement> clientSatisfactList;
    @FindBy (xpath = "//table[@class='table ']//td[2]")
    private List<WebElement> candidateSatisfactList;

    public String getName(){
        nameXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_name']";
        return driver.findElement(By.xpath(nameXpath)).getAttribute("title").toLowerCase();
    }
    public String getrefNmb(){
        refNmbXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_refNumber']";
        return driver.findElement(By.xpath(refNmbXpath)).getAttribute("title").toLowerCase();
    }
    public String getOffice(){
        officeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_assignedTo.officeName']";
        return driver.findElement(By.xpath(officeXpath)).getAttribute("title").toLowerCase();
    }
    public String getPersonType(){
        personTypeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_personTypeKeysurvey']";
        return driver.findElement(By.xpath(personTypeXpath)).getAttribute("title").toLowerCase();
    }
    public String getConsultant(){
        consultantXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_consultant']";
        return driver.findElement(By.xpath(consultantXpath)).getAttribute("title").toLowerCase();
    }
    public String getSurveyComplitionDate(){
        surveyCompletionDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_surveyCompletionDate']";
        return driver.findElement(By.xpath(surveyCompletionDateXpath)).getAttribute("title");
    }
    public String getAlertType(){
        alertTypeXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_alertType']";
        return driver.findElement(By.xpath(alertTypeXpath)).getAttribute("title").toLowerCase();
    }
    public String getStatus(){
        statusXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_status']";
        return driver.findElement(By.xpath(statusXpath)).getAttribute("title").toLowerCase();
    }
    public String getAlertDueDate(){
        alertDueDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_alertDueDate']";
        return driver.findElement(By.xpath(alertDueDateXpath)).getAttribute("title");
    }
    public String getLastModifyDate(){
        lastModifyDateXpath = "//tr[@id='"+alertID+"']/td[@aria-describedby='entitiesList_lastModifyDate']";
        return driver.findElement(By.xpath(lastModifyDateXpath)).getAttribute("title");
    }

    public void searchAlert(){
//        driver.navigate().refresh();
        waitForAJAXfinished();
        filterNameField.clear();
        filterNameField.sendKeys(CSVOperations.getCellFromAutofillCSVFile(3));
        new Select(filterAlertStatus).selectByValue("");
        filterSearchBtn.click();
        waitForLoadingDisappear();
        waitForAJAXfinished();
    }
}
