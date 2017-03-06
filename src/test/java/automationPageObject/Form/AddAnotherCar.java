package automationPageObject.Form;

import framework.elements.DropDown;
import framework.elements.Element;
import org.openqa.selenium.By;

public class AddAnotherCar {

    private String dropDownStringLocator = "//select[@id='%s-dropdown']";
    private String btnStringLocator = "//button[text()='%s']";

    public void selectValueFromDropDownByText(String dropDownName, String text) {
        new DropDown(By.xpath(String.format(dropDownStringLocator,dropDownName)),"DropDown[AddAnotherCar-form] '"+dropDownName+"'").selectItemByText(text);
    }

    public void clickButton(String buttonName) {
        new Element(By.xpath(String.format(btnStringLocator,buttonName)),"button 'Done'").click();
    }
    public void selectCarClickDone(String make, String model, String year, String buttonName) {
        selectValueFromDropDownByText("make",make);
        selectValueFromDropDownByText("model",model);
        selectValueFromDropDownByText("year",year);
        clickButton(buttonName);
    }
}
