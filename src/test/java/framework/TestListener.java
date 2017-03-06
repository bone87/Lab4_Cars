package framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestSuccess(ITestResult tr) {
        saveScreenShot();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        saveScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        saveScreenShot();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }

    @Attachment
    private byte[] saveScreenShot() {
        return ((TakesScreenshot)Browser.getBrowser().getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
