package automationPageObject;

import framework.elements.DropDown;
import framework.MyLogger;
import models.Car;
import steps.CompareCarsStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class CarReviewsPage extends BasePage {

    private String dropDownStringLocator = "//select[@name='%sDropDown']";
    private String allEnabledOptionsInSelectStringLocator = "//select[@ng-model='dropDowns.selected%s']/option[@label]";
    private final byte countOfTryToFindCar = 5;
    private MyLogger log = new MyLogger(CompareCarsStep.class);
    private String btnStringLocator = "//a[text()='%s']";

    public void selectValueFromDropDownByText(String dropDownName, String text) {
        new DropDown(By.xpath(String.format(dropDownStringLocator, dropDownName)), "DropDown '" + dropDownName + "'").selectItemByText(text);
    }

    private WebElement getRandomElement(String selectName) {
        List<WebElement> webElements = getDriver().findElements(By.xpath(String.format(allEnabledOptionsInSelectStringLocator, selectName)));
        return getRandomWebElement(webElements);
    }

    private WebElement getRandomMake() {
        return getRandomElement("Make");
    }

    private WebElement getRandomModel() {
        return getRandomElement("Model");
    }

    private WebElement getRandomYear() {
        return getRandomElement("Year");
    }

    private WebElement getRandomWebElement(List<WebElement> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public Car selectMakeModelYearAndGoToCostumerReviewsPage() {
        WebElement webElement = getRandomMake();
        String randomMakeValue = webElement.getText();
        selectValueFromDropDownByText("make", randomMakeValue);
        String randomModelValue = null;
        String randomYearValue = null;
        boolean flag;
        int count = 0;
        do {
            try {
                webElement = getRandomModel();
                randomModelValue = webElement.getText();
                selectValueFromDropDownByText("model", randomModelValue);
                webElement = getRandomYear();
                randomYearValue = webElement.getText();
                selectValueFromDropDownByText("year", randomYearValue);
                flag = true;
            } catch (RuntimeException e) {
                count++;
                if (count == countOfTryToFindCar) Assert.fail();
                log.info("\nCan't select 'YEAR' from the car with next parameters: " + randomMakeValue + " " + randomModelValue + ". Try to choose " + (count + 1) + "/5 time.\n");
                flag = false;
            }
        } while (!flag);
        clickLink(btnStringLocator, "Search","button 'Search'");
        return new Car(randomMakeValue, randomModelValue, randomYearValue);
    }
}
