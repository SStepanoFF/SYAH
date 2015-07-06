package framework.webDriver;

import framework.utils.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by sstep on 12/22/2014.
 */
public class Driver {
    public static WebDriver getInstance(){
        WebDriver webDriver = null;
        if (Loader.loadProperty("browser.name").isEmpty()){
            webDriver=WebDriverFactory.getDriver(DesiredCapabilities.firefox());
        }
        if (Loader.loadProperty("browser.name").equalsIgnoreCase("chrome")){
            webDriver = WebDriverFactory.getDriver(DesiredCapabilities.chrome());
        }
        if (Loader.loadProperty("browser.name").equalsIgnoreCase("IE")){
            webDriver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
        }
        return webDriver;
    }
}
