import framework.utils.CSVOperations;
import framework.webDriver.Driver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 13/07/2015.
 */
public class CommonOperationsTest  {

    @BeforeSuite
    public void setUpTest() {
    }

        @Test
    private void createCSVAutofillFileTest(){
        CSVOperations.createCSVAutofillFile();
    }
}
