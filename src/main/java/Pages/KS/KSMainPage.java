package Pages.KS;

import Pages.Surveys.SurveyPage;
import framework.DriverOperations;
import framework.utils.GlobalVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by sergii.stepanov on 12/06/2015.
 */
public class KSMainPage extends DriverOperations {

    public KSMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "searchInput")
    private WebElement searchField;
    @FindBy (id = "search-button")
    private WebElement searchBtn;
    @FindBy (partialLinkText = "Manage responses")
    private WebElement manageResponsLink;
    @FindBy (css = "a[title='Contacts']")
    private WebElement contactsTabBtn;
    @FindBy (css = "a[title='Complete Respondents']")
    private WebElement complitedResponceLinkBtn;
    @FindBy (id = "LayerLoad")
    public WebElement loadingSymbl;
    @FindBy (css = "a[name='LABEL_SURVEY']")
    private WebElement surveyTabBtn;

    public KSManageResponsePage openManageResponsePage(){
        searchField.sendKeys(GlobalVariables.KSSurveyID);
        searchBtn.click();
        waitForAJAXfinished();
        manageResponsLink.click();
        waitForAJAXfinished();
        return new KSManageResponsePage(driver);
    }

    public SurveyPage openComplitedSurveys(){
        surveyTabBtn.click();
        waitForAJAXfinished();
        searchField.sendKeys(GlobalVariables.KSSurveyID);
        searchBtn.click();
        waitForAJAXfinished();
        complitedResponceLinkBtn.click();
        return new SurveyPage(driver);
    }

    public KSContactsPage openContactsPage(){
        contactsTabBtn.click();
        return new KSContactsPage(driver);
    }

    public void waitForLoadingDisapp(){
        WebDriverWait wait = new WebDriverWait(driver,600);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingSymbl)));
    }
}
