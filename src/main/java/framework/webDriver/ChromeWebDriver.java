package framework.webDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

/**
 * Created by sstep on 12/22/2014.
 */
public class ChromeWebDriver {
    public static void getInstance() {
        String chromeFile=System.getProperty("user.dir")+"\\src\\main\\resources\\webDriver\\chrome\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeFile);
//        return new ChromeDriver();
        File chromeDir = new File(chromeFile);
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(chromeDir);
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\XLSFile\\");
        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--KS_API_FormResultManagementService-type");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
    }
}
