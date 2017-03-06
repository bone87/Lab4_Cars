package framework.elements;

import framework.Browser;
import framework.MyLogger;
import framework.Property;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Element {
    protected WebDriver driver = Browser.getBrowser().getDriver();
    protected By locator;
    protected String text;
    protected MyLogger log = new MyLogger(Element.class);

    public Element(By locator, String text) {
        this.locator = locator;
        this.text = text;
    }

    public WebElement getWebElement() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return new WebDriverWait(driver, Property.getProperties().getTimeForExplicitWait()).pollingEvery(2,TimeUnit.SECONDS).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try{
                    return driver.findElement(locator);
                } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
                    return null;
                } finally {
                    driver.manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait(),TimeUnit.SECONDS);

                }
            }
        });
    }

    public void click() {
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        new WebDriverWait(driver,Property.getProperties().getTimeForExplicitWait()).pollingEvery(1, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try{
                    getWebElement().click();
                    return true;
                } catch (WebDriverException e) {
                    return false;
                }
            }
        });

        log.info(text+" has clicked");
        driver.manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait(),TimeUnit.SECONDS);

    }

    public String getText() {
        return getWebElement().getText();
    }

    public boolean isElement() {
    driver.manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait()/3,TimeUnit.SECONDS);
        boolean flag = false;
        try
        {
            driver.findElement(locator);
            log.info(text+" has appeared");
            flag = true;
        } catch (NoSuchElementException e) {
            log.info(text+" hasn't appeared");
            flag = false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait(),TimeUnit.SECONDS);
        }
        return flag;
    }

}
