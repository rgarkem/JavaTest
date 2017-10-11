package cucumberTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "Features", 
		glue = {"stepDefinitions"}, 
		format = {"html:SearchResults"}, 
		dryRun = false)
public class TestRunner {

}