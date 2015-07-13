
import KS_API_FormResultManagementService.FormResultManagementLocator;
import KS_API_FormResultManagementService.FormResultManagementService;
import KS_API_FormResultManagementService.WSRespondent;
import Pages.KS.KSLoginPage;
import Pages.KS.KSMainPage;
import Pages.KS.KSManageResponsePage;
import framework.CommonOperations;
import framework.DriverOperations;
import framework.utils.CSVOperations;
import framework.utils.GlobalVariables;
import framework.webDriver.Driver;
import org.apache.axis.client.Stub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

/**
* Created by sergii.stepanov on 20.01.2015.
*/
public class test extends CommonOperations{

    public static void main(String []args) throws Exception {
        WebDriver driver=Driver.getInstance();
        driver.navigate().to(GlobalVariables.KSUrl);
        DriverOperations.createNewWindow(driver, "http://link2");
        DriverOperations.switchWindow(driver,0);
        driver.navigate().to("http://link3");
//        DriverOperations.switchTab(driver);
    }

}

