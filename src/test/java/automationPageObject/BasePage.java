package automationPageObject;

import automationPageObject.Form.Header;
import framework.Browser;
import framework.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;
    private Header header = new Header();

    public BasePage() {
        this.driver = Browser.getBrowser().getDriver();
    }

    public Header getHeader() {
        return header;
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void clickLink(String stringLocator, String linkName,String message) {
        new Element(By.xpath(String.format(stringLocator, linkName)), message).click();
    }

}
