package automationPageObject;

public class ConsumerReviewsPage extends BasePage {

    private String linkStringLocator = "//a[text()='%s']";

    public void clickViewDetailsGoToCarDetailsPage(String linkName) {
        clickLink(linkStringLocator,linkName, "link '" + linkName + "'");
    }

}
