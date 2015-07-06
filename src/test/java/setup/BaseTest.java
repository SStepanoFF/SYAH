package setup;

import framework.utils.Loader;
import framework.webDriver.Driver;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTest {

     public WebDriver driver;

    @BeforeSuite
    public void setUpTest(){
        driver = Driver.getInstance();
//        driver.manage().window().maximize(); //setSize(new Dimension(1500,1080));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() { //ITestContext context
        //driver = getDriver(context);
        if (driver != null) {
//            driver.quit();
        }
   }

    protected void createNewTab(WebDriver driver, String url) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        driver.navigate().to(url);
    }
//
//    protected void switchTab(WebDriver driver) {
//        try{
//            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
//            ArrayList tabs = new ArrayList(driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(0).toString());  //driver.switchTo().defaultContent();
//        }catch (Exception e){
//            Loader.logWritter("ERROR! Couldn't switch tab");
//        }
//    }
//
protected void createNewWindow(WebDriver driver, String url) {
    try {
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])");
        switchWindow(driver, 1);
        driver.navigate().to(url);
    } catch (Exception e) {
        Loader.logWritter("ERROR! Couldn't load second page");
    }
}
//
protected void switchWindow(WebDriver driver, int number) {
    try {
        driver.switchTo().window(driver.getWindowHandles().toArray()[number].toString());
    } catch (Exception e) {
        Loader.logWritter("ERROR! Couldn't switch tab");
    }
}

    @AfterMethod
    public void takeScreenShot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {     //условие делать скриншот если тест FAIL
            DateTime dt = new DateTime();
            DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd_MM_yyyy_HH_mm");
            String resultDateTime = dateFormat.print(dt);
//            Report.log(++step, "Test: " + result.getTestContext().getCurrentXmlTest().getName()
//                    + "_" + result.getName(), Report.State.PASS);
            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File outputDir = new File(result.getTestContext().getOutputDirectory());
            File saved = new File(System.getProperty("user.dir") + "\\Screenshots\\", resultDateTime + "_" + result.getTestContext().getCurrentXmlTest().getName() + "_" + result.getName() + ".png");
            FileUtils.copyFile(f, saved);
        }
    }
}
