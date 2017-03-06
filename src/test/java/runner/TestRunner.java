package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import framework.TestListener;
import org.testng.annotations.Listeners;

@CucumberOptions(features = "src/test/java/features",
                glue = "steps",
                plugin = {"json:target/cucumber.json"})
@Listeners(TestListener.class)
public class TestRunner extends AbstractTestNGCucumberTests{

}
