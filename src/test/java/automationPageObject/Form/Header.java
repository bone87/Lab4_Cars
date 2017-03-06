package automationPageObject.Form;

import framework.elements.Element;
import org.openqa.selenium.By;

public class Header {

    private String itemNavMenuLocator = "//a//*[text()='%s']/..//*[@class='icon-image']";
    private String subMenuItemLocator = "//a[contains(text(),'%s')]";

    public void selectItemFromNavMenu(String itemName) {
        new Element(By.xpath(String.format(itemNavMenuLocator,itemName)),"item '"+itemName+"' chevron").click();
    }

    public void selectItemFromSubmenu(String submenu, String itemName) {
        selectItemFromNavMenu(submenu);
        new Element(By.xpath(String.format(subMenuItemLocator,itemName)),"item '"+itemName+"' from submenu '"+submenu+"'").click();

    }

}
