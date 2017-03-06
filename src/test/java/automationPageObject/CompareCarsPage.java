package automationPageObject;

import automationPageObject.Form.AddAnotherCar;
import framework.elements.Element;
import framework.Property;
import models.Car;
import models.CarSpec;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareCarsPage extends BasePage {

    private AddAnotherCar addAnotherCarForm = new AddAnotherCar();
    private String featureStringLocator = "(//*[@header='%s']//span)[%d]";
    private String carMakeModelYearStringLocator = "(//*[@class='listing-name'])[%d]";
    private String buttonStringLocator = "//div[@id='icon-div']";
    private String btnRemoveStringLocator = "(//*[@id='image-header']/span)[%d]//*[@class='remove']";

    public String getFeatureValueForCar(String feature, int carNumber) {
        return new Element(By.xpath(String.format(featureStringLocator, feature, carNumber)), "feature '" + feature + "' for " + carNumber + "th car").getText();
    }

    public String getCarMakeModelYear(int carNumber) {
        return new Element(By.xpath(String.format(carMakeModelYearStringLocator, carNumber)), "make, model and year for " + carNumber + "th car").getText();
    }

    public void goToAddAnotherCarForm() {
        clickLink(buttonStringLocator, "", "button 'Add Another Car'");
    }

    public AddAnotherCar getAddAnotherCarForm() {
        return addAnotherCarForm;
    }

    public Car getTableCar(int carNumber) {
        new WebDriverWait(getDriver(), Property.getProperties().getTimeForExplicitWait()).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return new Element(By.xpath(String.format(btnRemoveStringLocator,carNumber)),"button 'Remove' for "+carNumber+" car").isElement();
            }
        });
        return new Car(getCarMakeModelYear(carNumber), new CarSpec(getEngineValue(carNumber), getTransmissionValue(carNumber)));
    }

    private String getEngineValue(int carNumber) {
        return getFeatureValueForCar("Engine", carNumber);
    }

    private String getTransmissionValue(int carNumber) {
        return getFeatureValueForCar("Transmission", carNumber);
    }

}
