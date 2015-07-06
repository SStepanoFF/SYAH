package Pages.KS;

import framework.DriverOperations;
import framework.utils.GlobalVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public KSManageResponsePage openManageResponsePage(){
        searchField.sendKeys(GlobalVariables.KSSurveyID);
        searchBtn.click();
        waitForAJAXfinish();
        manageResponsLink.click();
        waitForAJAXfinish();
        return new KSManageResponsePage(driver);
    }
}
