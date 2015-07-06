//import io.selendroid.SelendroidDriver;
//import io.selendroid.SelendroidCapabilities;
//import io.selendroid.device.DeviceTargetPlatform;
//import io.selendroid.SelendroidConfiguration;
//import io.selendroid.SelendroidLauncher;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//public class test {
//
//
////import io.selendroid.standalone.SelendroidConfiguration;
////import io.selendroid.standalone.SelendroidLauncher;
//
//    private String application = "apk_signed.apk";
//    private String pack = "com.form.nba.offline:4.0.21";
//    private long portalId = 111111;
//   // private static String location = AppUtil.getRelativePathToImageWithClass(SUP_10536.class.getResource("/"), "resources/");
//    private WebDriver webDriver;
//
//    @BeforeClass
//    public void beforeClass(){
//        SelendroidConfiguration config = new SelendroidConfiguration();
//        config.addSupportedApp("src/main/resources/apk_signed.apk");
//        SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
//        selendroidServer.lauchSelendroid();
//        try {
//            webDriver = new SelendroidDriver("http://localhost:4444/wd/hub", SelendroidCapabilities.device(DeviceTargetPlatform.ANDROID19, pack));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void test(){
////        if(!webDriver.getWindowHandle().equals("WEBVIEW")){
////            webDriver.switchTo().window("WEBVIEW");
////        }
//        webDriver.findElement(By.id("usernameField")).sendKeys("http://ksbeta.pr1.ssstest.com");
//
//    }
//
//}




import Pages.KS.KSLoginPage;
import Pages.KS.KSMainPage;
import Pages.KS.KSManageResponsePage;
import framework.CommonOperations;
import framework.utils.CSVOperations;
import framework.webDriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

/**
* Created by sergii.stepanov on 20.01.2015.
*/
public class test extends CommonOperations{


    public static void main(String []args) {
        String text="English (UK) (9)<language selection=\"en\"/>";
        CSVOperations.getCellFromAutofillCSVFile(0);
//        String res= parsTextByRegEx(text,"\\d");
//        System.out.println(res);
//         KSLoginPage ksLoginPage;
//         KSMainPage ksMainPage;
//        KSManageResponsePage ksManageResponsePage;
//
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("browser.download.folderList", 2);
//        profile.setPreference("browser.download.dir",System.getProperty("user.dir") + "\\XLSFile\\"); //
//        profile.setPreference("browser.download.manager.showWhenStarting", false);
//        profile.setPreference("browser.helperApps.neverAsk.openFile",
//                "application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel");
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//                "application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel");
//        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
//        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
//        profile.setPreference("browser.download.manager.focusWhenStarting", false);
//        profile.setPreference("browser.download.manager.useWindow", false);
//        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
//        profile.setPreference("browser.download.manager.closeWhenDone", false);
//        WebDriver driver= new FirefoxDriver(profile);
//
//        ksLoginPage=new KSLoginPage(driver);
//        ksMainPage=ksLoginPage.loginKS();
//        ksManageResponsePage=ksMainPage.openManageResponsePage();
//        ksManageResponsePage.exportContactsToExcel();
    }
}
