package framework.utils;

import framework.webDriver.Driver;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;


import java.io.File;
import java.io.IOException;

/**
 * Created by sergii.stepanov on 03/06/2015.
 */
public class Screenshot {

    WebDriver driver;

    public Screenshot() {
        driver = Driver.getInstance();
    }

    public void onTestFailure(ITestContext testContext) {
        DateTime dt = new DateTime();
        DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd_MM_yyyy");
        String resultDateTime = dateFormat.print(dt);
//            Report.log(++step, "Test: " + result.getTestContext().getCurrentXmlTest().getName()
//                    + "_" + result.getName(), Report.State.PASS);
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File outputDir = new File(result.getTestContext().getOutputDirectory());
        File saved = new File(System.getProperty("user.dir") + "\\screenshots\\", resultDateTime + "_" + testContext.getName() + ".png");
        try {
            FileUtils.copyFile(f, saved);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
