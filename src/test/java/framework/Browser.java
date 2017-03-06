package framework;

import org.openqa.selenium.WebDriver;

public class Browser {

    private static WebDriver driver = null;
    private static Browser browser;

    private Browser(){
        driver = BrowserFactory.getBrowserDriver(Property.getProperties().getType());
    }

    public static Browser getBrowser(){
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
