package framework.webDriver;

/**
 * Created by Sergey on 22.12.2014.
 */
public class IEWebDriver {
    public static void getInstance() {
        String IEFile = System.getProperty("user.dir")+"\\src\\main\\resources\\webDriver\\IE\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", IEFile);
//        return new InternetExplorerDriver();
    }
}
