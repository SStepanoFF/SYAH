package Pages.Portal;

import framework.DriverOperations;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by sergii.stepanov on 02/07/2015.
 */
public class PortalMainPage extends DriverOperations {

    public PortalMainPage(WebDriver driver) {
        super(driver);
         wait.until(ExpectedConditions.visibilityOf(alertsTab));
    }

    @FindBy (css = "a[href='/Alerts.action']")
    private WebElement alertsTab;

    @FindBy(xpath = "//*[contains(text(), 'Loading')]")
    public WebElement loadingMessage;

    public AlertTabPage openAlertTabPage(){
        alertsTab.click();
        return new AlertTabPage(driver);
    }

    public void waitForLoadingDisappear(){
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingMessage)));
    }
}
