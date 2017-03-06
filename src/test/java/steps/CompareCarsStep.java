package steps;

import automationPageObject.*;
import framework.*;
import models.Car;
import models.CarSpec;
import models.CarStore;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class CompareCarsStep {
    private WebDriver driver;
    private MainPage mainPage = new MainPage();
    private CarReviewsPage carReviewsPage = new CarReviewsPage();
    private ConsumerReviewsPage consumerReviewsPage = new ConsumerReviewsPage();
    private CarDetailsPage carDetailsPage = new CarDetailsPage();
    private CarTrimPage carTrimPage = new CarTrimPage();
    private CompareCarsPage compareCarsPage;
    private Car car;

    private MyLogger log = new MyLogger(CompareCarsStep.class);
    private SoftAssert softAssert = new SoftAssert();

    @Step("Setup")
    public void setup(){
        driver = Browser.getBrowser().getDriver();
        PropertyConfigurator.configure(Property.getProperties().getLo4jPath());
        log.startLog();
    }

    @Given("^Go to \"([^\"]*)\"$")
    @Severity(SeverityLevel.BLOCKER)
    @Step("Step 1")
    public void loadMainPage(String url) {
        setup();
        log.stepInfo("","Navigate to "+url);
        driver.get(url);
    }

    @When("^Select item \"([^\"]*)\" from menu \"([^\"]*)\"$")
    @Step("Step 2")
    public void selectBuyReviewACar(String itemName, String menuName) {
        log.stepInfo("","Select "+menuName+" -> "+itemName);
        mainPage.getHeader().selectItemFromSubmenu(menuName,itemName);
    }

    @When("^Find a car at random values$")
    @Step("Step 3")
    public void findCarAtRandomValues() {
        log.stepInfo("","Find a random car in section 'Read or Write Reviews'");
        car = carReviewsPage.selectMakeModelYearAndGoToCostumerReviewsPage();
    }

    @When("^On the page 'Reviews' click to link \"([^\"]*)\"$")
    @Step("Step 4")
    public void clickToLinkModelDetails(String linkName) {
        log.stepInfo("","On the page 'Reviews' click to link 'Model Details'");
        consumerReviewsPage.clickViewDetailsGoToCarDetailsPage(linkName);
    }

    @When("^On the tab 'Trims' select \"([^\"]*)\" modification of the car$")
    @Step("Step 5")
    public boolean checkTheCarCanBeCompare(String numberOfTrim) {
        log.stepInfo("","On the tab 'Trims' select first modification of the car");
        return carDetailsPage.selectTrimAndViewDetails(numberOfTrim);
    }

    @When("^Save the characteristics of the car \"([^\"]*)\" for next comparing$")
    @Step("Step 6")
    public void addCarToGarage(String carNumber) {
        log.stepInfo("","Save specifications of the car for next comparing");
        CarSpec carSpec = carTrimPage.getCarSpec();
        car.setCarSpec(carSpec);
        CarStore.addCarToGarage(carNumber,car);
    }

    @When("^Select item \"([^\"]*)\" from menu \"([^\"]*)\", find a car at random values at the \"([^\"]*)\" attempt, click to link \"([^\"]*)\", select \"([^\"]*)\" trim, save the car \"([^\"]*)\" for next comparing$")
    @Step("Step 7")
    public void selectItemFromMenuFindCarAtRandomValuesClickToLinkSelectTrimSaveTheCarForNextComparing(String itemName, String menuName, int countOfAttemps, String linkName, String numberOfTrim, String carName) throws Throwable {
        log.stepInfo("","Select and save car for the next comparing");
        int attemp = 1;
        do {
            log.info("Attemp: "+attemp+"/"+countOfAttemps);
            selectBuyReviewACar(itemName, menuName);
            findCarAtRandomValues();
            clickToLinkModelDetails(linkName);
            attemp++;
            if (attemp>countOfAttemps) Assert.fail("Attemps to find a car has ended");
        } while (!checkTheCarCanBeCompare(numberOfTrim));
        addCarToGarage(carName);
    }


    @When("^View garage$")
    @Step("Step 8")
    public void viewGarage()  {
        CarStore.viewGarage();
    }

    @When("^Go to the tab \"([^\"]*)\", select first available modification to compare, click button \"([^\"]*)\"$")
    @Step("Step 9")
    public void selectFirstTrimCheckCompareClickButton(String tabName, String buttonName) {
        log.stepInfo("","Save specifications of the car for next comparing");
        carTrimPage.selectTabCheckCompareAndClickButton(tabName, buttonName);
    }

    @When("^Click button 'Add Another Car', select \"([^\"]*)\" car and click button \"([^\"]*)\"$")
    @Step("Step 10")
    public void addAnotherCarToCompare(String carName, String button2Name) {
        log.stepInfo("","Select car for comparing (Click button 'Add Another Car' and select car)");
        compareCarsPage = new CompareCarsPage();
        compareCarsPage.goToAddAnotherCarForm();
        Car car = CarStore.getCarFromGarage(carName);
        compareCarsPage.getAddAnotherCarForm().selectCarClickDone(car.getMake(),car.getModel(),car.getYear(),button2Name);
    }

    @When("^Check the ComparePage for \"([^\"]*)\" car from garage and \"([^\"]*)\" car from the table$")
    @Step("Step 11")
    public void checkTheComparePageForCars(String garageCarName,int tableCarNumber) {
        log.stepInfo("","Compare the cars");
        compareGarageCarWithTableCar(garageCarName,tableCarNumber);
    }

    @Step("Step 12")
    private void compareGarageCarWithTableCar(String garageCarName,int tableCarNumber) {
        log.info("\nCompare "+garageCarName+" car with the "+tableCarNumber+"th car from the table");
        Car carFromGarage = CarStore.getCarFromGarage(garageCarName);
        Car carFromTable = compareCarsPage.getTableCar(tableCarNumber);
        softAssert.assertActContainsExp(carFromGarage.getMake(),carFromTable.getMake(),"verify 'Make':");
        softAssert.assertActContainsExp(carFromGarage.getModel(),carFromTable.getMake(),"verify 'Model':");
        softAssert.assertActContainsExp(carFromGarage.getYear(),carFromTable.getMake(),"verify 'Year':");
        softAssert.assertActContainsExp(carFromGarage.getCarSpec().getEngine(),carFromTable.getCarSpec().getEngine(),"verify 'Engine':");
        softAssert.assertActContainsExp((carFromGarage.getCarSpec().getTransmission()),carFromTable.getCarSpec().getTransmission(),"verify 'Transmission':");
    }

    @Then("^Garage cars equals table cars$")
    @Step("Step 13")
    public void garageCarsEqualsTableCars(){
        teardown();
        softAssert.assertAll();
        stopLogging();
    }

    @Step("Teardown")
    public void teardown() {
        driver.quit();
    }

    public void stopLogging()
    {
        log.stopLog();
    }

}
