package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends Element {

    public DropDown(By locator, String text) {
        super(locator, text);
    }

    public void selectItemByText(String textValue) {
        Select select = new Select(getWebElement());
        select.selectByVisibleText(textValue);
        log.info("Selected value="+textValue+" in "+text);
    }
}
