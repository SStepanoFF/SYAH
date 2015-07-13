package Pages.KS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by sergii.stepanov on 08/07/2015.
 */
public class KSContactsPage extends KSMainPage {

    public KSContactsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(importBtn));
    }

    @FindBy (css = "a[title='Import']")
    private WebElement importBtn;
    @FindBy (css = "button[name='newSync']")
    private WebElement setupNewImportBtn;
    @FindBy (css = "button[name='editSync']")
    private WebElement editExistedContactManager;

    @FindBy (css = "label[for='urlTypeL']")
    private WebElement youCompSelectionRadioBtn;


    private void selectContactsGroup(){
        driver.switchTo().frame(driver.findElement(By.id("treeViewId")));
        driver.findElement(By.xpath("//*[contains(text(),'testContacts')]")).click();
        waitForAJAXfinished();
        waitForLoadingDisapp();
        driver.switchTo().defaultContent();
    }

    public void setAutofillFile(){
        selectContactsGroup();
        importBtn.click();
        waitForLoadingDisapp();
        editExistedContactManager.click();
        youCompSelectionRadioBtn.click();
    }
}
