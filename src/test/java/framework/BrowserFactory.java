package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    public enum BrowserType{
        FIREFOX,
        CHROME,
    }

    public static WebDriver getBrowserDriver(BrowserType type) {
        WebDriver driver;

        switch (type) {
            case FIREFOX:
                if (isWindows()) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                } else if (isUnix()) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                } else if (isMac()) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver_mac");
                }
                driver = new FirefoxDriver();
                break;
            case CHROME:
                if (isWindows()) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                } else if (isUnix()) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                } else if (isMac()) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac");
                }
                driver = new ChromeDriver();
                break;

            default: driver = new FirefoxDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Property.getProperties().getTimeForImplicitlyWait(),TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Property.getProperties().getTimeForImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static boolean isWindows(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "win" ) >= 0);
    }

    public static boolean isUnix (){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
    }

    public static boolean isMac (){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf( "mac") >=0);
    }

}
