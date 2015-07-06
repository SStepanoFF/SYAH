package Pages.KS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

/**
 * Created by sergii.stepanov on 12/06/2015.
 */
public class KSManageResponsePage extends KSMainPage {

    public KSManageResponsePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loadingImg)));
    }

    @FindBy (id = "LayerLoad")
    private WebElement loadingImg;
    @FindBy (id = "exportContacts")
    private WebElement exportBtn;
    @FindBy (partialLinkText = "Excel")
    private WebElement exportToExcelBtn;

    public void exportContactsToExcel(){
        exportBtn.click();
        exportToExcelBtn.click();
        waitForAJAXfinish();
        File file = new File(System.getProperty("user.home") + "\\Downloads\\ContactsOfGroup-Excel.xls");
        while (!file.exists()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
