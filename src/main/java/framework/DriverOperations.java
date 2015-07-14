package framework;


import framework.utils.DataBase;
import framework.utils.GlobalVariables;
import framework.utils.Loader;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class DriverOperations extends CommonOperations {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    private Robot rob = null;
    private DataBase dataBase;

    public DriverOperations(WebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(GlobalVariables.GlobalTimeOut, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Integer.parseInt(Loader.loadProperty("timeout")));
    }

    public boolean isElementPresent(By element) {
        try {
            driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
            if (driver.findElement(element).isDisplayed()) return true;
            else return false;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(Loader.loadProperty("timeout")), TimeUnit.SECONDS);
        }
    }

    public void waitAndClick(WebElement webElement){
//        WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(Loader.loadProperty("timeout")));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public static void createNewTab(WebDriver driver, String url) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        driver.navigate().to(url);
    }

    public static void switchTab(WebDriver driver) {
        try{
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
            ArrayList tabs = new ArrayList(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0).toString());  //driver.switchTo().defaultContent();
        }catch (Exception e){
            Loader.logWritter("ERROR! Couldn't switch tab");
        }
    }

    public static void createNewWindow(WebDriver driver, String url) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])");
            switchWindow(driver, 1);
            driver.navigate().to(url);
        } catch (Exception e) {
            Loader.logWritter("ERROR! Couldn't load second page");
        }
    }

    public static void switchWindow(WebDriver driver, int number) {
        try {
            driver.switchTo().window(driver.getWindowHandles().toArray()[number].toString());
        } catch (Exception e) {
            Loader.logWritter("ERROR! Couldn't switch tab");
        }
    }

    public void mouseMoveTo(WebElement element) {
        actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

//    public void mouseMoveSikuli(String imagePath) {
//        Screen screen = new Screen();
//        Pattern img = new Pattern(imagePath);
//        try {
//            screen.click(img);
//        } catch (FindFailed findFailed) {
//            findFailed.printStackTrace();
//        }
//    }

    public void mouseMoveRobot(WebElement element) {
        waitForAJAXfinished();
        Point coord = element.getLocation();
        Point browserCoord = driver.manage().window().getPosition();
        try {
            rob = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        } finally {
            rob.mouseMove(coord.getX() + browserCoord.getX() + 20, coord.getY() + browserCoord.getY() + 100);
        }
    }

    public void mouseWheel(int duration) {
        try {
            rob = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        } finally {
            rob.mouseWheel(duration);
        }
    }

    public void waitForPageLoad() {
//            ExpectedCondition<Boolean> pageLoadCondition = new
//                    ExpectedCondition<Boolean>() {
//                        public Boolean apply(WebDriver driver) {
//                            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
//                        }
//                    };
//            WebDriverWait wait = new WebDriverWait(driver, 30);
//            wait.until(pageLoadCondition);
//        {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            }
        });
    }

    public void waitForAJAXfinished() {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(org.openqa.selenium.WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        });
    }
}