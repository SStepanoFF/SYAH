package Pages.KS;

import framework.DriverOperations;
import framework.utils.GlobalVariables;
import framework.utils.Loader;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by sergii.stepanov on 11/06/2015.
 */
public class KSLoginPage extends DriverOperations {

    public KSLoginPage(WebDriver driver) {
        super(driver);
        driver.get(GlobalVariables.KSUrl);
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
    }

    @FindBy (id = "login")
    private WebElement loginField;
    @FindBy (id = "password")
    private WebElement passField;
    @FindBy (id="loginButton")
    private WebElement loginBtn;

    public KSMainPage loginKS(){
        loginField.sendKeys(GlobalVariables.KSUsername);
        passField.sendKeys(GlobalVariables.KSUserPass);
        loginBtn.click();
        wait = new WebDriverWait(driver, 1);
        try{
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }catch (TimeoutException ex){ }
        return new KSMainPage(driver);
    }
}
