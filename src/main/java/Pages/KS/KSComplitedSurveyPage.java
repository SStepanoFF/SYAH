package Pages.KS;

import Pages.Surveys.SurveyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by sergii.stepanov on 08/07/2015.
 */
public class KSComplitedSurveyPage extends SurveyPage {

    public KSComplitedSurveyPage(WebDriver driver) {
        super(driver);
//        switchWindow(driver,1);
    }

    @FindBy (css = "button[title='Click here to view the previous respondent']")
    private WebElement prefPageBtn;
    @FindBy (css = "button[title='Click here to view the last response']")
    private WebElement lastPageBtn;
    @FindBy (className = "FormSelectBox")
    private WebElement pageNmbField;


}
