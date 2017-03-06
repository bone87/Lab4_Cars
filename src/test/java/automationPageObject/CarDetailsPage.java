package automationPageObject;

import framework.elements.Element;
import org.openqa.selenium.By;

public class CarDetailsPage extends BasePage {

    private Element chkboxCompare = new Element(By.xpath("//label[@id='checkbox-trim-' and text()='Compare']"), "checkBox 'Compare'");

    private String linkStringLocator = "(//a[@cars-common-omniture-standard='View Specs'])[%s]";

    public boolean selectTrimAndViewDetails(String numberOfTrim) {
        if (chkboxCompare.isElement()) {
            clickLink(linkStringLocator,numberOfTrim, "link 'View Details' for " + numberOfTrim + "th modification");
            return true;
        } else return false;
    }


}
