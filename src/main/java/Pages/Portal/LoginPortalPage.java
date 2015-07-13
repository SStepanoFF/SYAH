package Pages.Portal;

import framework.DriverOperations;
import framework.utils.GlobalVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by sergii.stepanov on 02/07/2015.
 */
public class LoginPortalPage extends DriverOperations {

    public LoginPortalPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(emailField));
    }

    @FindBy (id = "login")
    private WebElement emailField;
    @FindBy (id = "password")
    private WebElement passField;
    @FindBy (css = "input[type='submit']")
    private WebElement loginBtn;

    public void loginPortal(){
        emailField.sendKeys(GlobalVariables.getPortalLogin());
        passField.sendKeys(GlobalVariables.getPortalPass());
        loginBtn.click();
    }
}
