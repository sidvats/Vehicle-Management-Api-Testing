package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features="src/test/resources/Features/E2ETestingFakeApi.feature",glue="StepDefinition", plugin= {"pretty","html:target/cucumber/reports.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},tags="@Put_Positive")
public class TestRunner extends  AbstractTestNGCucumberTests{

}

//tags="@Post_Positive"
//tags="@Delete_Positive"