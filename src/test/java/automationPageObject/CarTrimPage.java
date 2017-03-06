package automationPageObject;

import framework.elements.Element;
import framework.Property;
import models.CarSpec;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CarTrimPage extends BasePage {

    private Element txtAreaEngine = new Element(By.xpath("//*[@id='engine']"), "text area 'Engine'");
    private Element txtAreaTransmission = new Element(By.xpath("//*[@id='transmission']"), "text area 'Transmission'");
    private Element chkboxCompare = new Element(By.xpath("(//div[@class='trim_listing__footer'])[1]/div[2]"), "checkbox 'Compare'");
    private String buttonStringLocator = "//button[text()='%s']";
    private String linkStringLocator = "//a[@class='menu-item' and text()=' %s ']";

    public CarSpec getCarSpec() {
        return new CarSpec(getEngineSpec(), getTransmissionSpec());
    }

    private String getEngineSpec() {
        return txtAreaEngine.getText();
    }

    private String getTransmissionSpec() {
        return txtAreaTransmission.getText();
    }

    public void selectTabCheckCompareAndClickButton(String tabName, String buttonName) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(getDriver(), Property.getProperties().getTimeForExplicitWait()).pollingEvery(5, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
//                    clickLink(linkStringLocator,"link '"+tabName+"'");
                    chkboxCompare.click();
                    clickLink(buttonStringLocator,buttonName, "button '" + buttonName + "'");
                    return true;
                } catch (WebDriverException e) {
                    return false;
                }
            }
        });
        getDriver().manage().timeouts().implicitlyWait(Property.getProperties().getTimeForImplicitlyWait(), TimeUnit.SECONDS);
    }
}
